package com.chukmaldin.notes.data

import com.chukmaldin.notes.domain.ContentItem
import com.chukmaldin.notes.domain.Note
import com.chukmaldin.notes.domain.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl private constructor(
    private val notesDao: NotesDao,
    private val imageFileManager: ImageFileManager
): NotesRepository {

    override suspend fun addNote(
        title: String,
        content: List<ContentItem>,
        isPinned: Boolean,
        updatedAt: Long
    ) {
        val processedContent = content.processForStorage()
        val noteDbModel = NoteDbModel(0, title, updatedAt, isPinned)
        notesDao.addNoteWithContent(noteDbModel, processedContent)
    }

    override suspend fun deleteNote(noteId: Int) {
        val note = notesDao.getNote(noteId).toEntity()
        notesDao.deleteNote(noteId)

        note.content
            .filterIsInstance<ContentItem.Image>()
            .map { it.url }
            .forEach {
                imageFileManager.deleteImage(it)
            }
    }

    override suspend fun editNote(note: Note) {
        val oldNote = notesDao.getNote(note.id).toEntity()

        val oldUrls = oldNote.content
            .filterIsInstance<ContentItem.Image>()
            .map { it.url }
        val newUrls = note.content
            .filterIsInstance<ContentItem.Image>()
            .map { it.url }
        val removedUrls = oldUrls - newUrls.toSet()
        removedUrls.forEach {
            imageFileManager.deleteImage(it)
        }

        val processedContent = note.content.processForStorage()

        notesDao.editNoteWithContent(note.toDbModel(), processedContent)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map {
            it.toEntities()
        }
    }

    override suspend fun getNode(noteId: Int): Note {
        return notesDao.getNote(noteId).toEntity()
    }

    override fun searchNotes(query: String): Flow<List<Note>> {
        return notesDao.searchNotes(query).map {
            it.toEntities()
        }
    }

    override suspend fun switchPinnedStatus(noteId: Int) {
        notesDao.switchPinnedStatus(noteId)
    }

    private suspend fun List<ContentItem>.processForStorage(): List<ContentItem> {
        return map { contentItem ->
            when (contentItem) {
                is ContentItem.Image -> {
                    if (imageFileManager.isInternal(contentItem.url)) {
                        contentItem
                    } else {
                        val internalPath = imageFileManager.copyImageToInternalStorage(contentItem.url)
                        contentItem.copy(url = internalPath)
                    }
                }
                is ContentItem.Text -> contentItem
            }
        }
    }

    companion object {

        private val LOCK = Any()
        private var instance: NotesRepositoryImpl? = null

        fun getInstance(
            notesDao: NotesDao,
            imageFileManager: ImageFileManager
        ): NotesRepositoryImpl {
            instance?.let {
                return it
            }

            synchronized(LOCK) {
                instance?.let {
                    return it
                }

                return NotesRepositoryImpl(notesDao, imageFileManager).also {
                    instance = it
                }
            }
        }
    }
}