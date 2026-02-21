//package com.chukmaldin.notes.data
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.currentComposer
//import androidx.compose.runtime.mutableStateOf
//import com.chukmaldin.notes.domain.Note
//import com.chukmaldin.notes.domain.NotesRepository
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.flow.update
//
//object TestNotesRepositoryImpl : NotesRepository {
//
//    private val testData = mutableListOf<Note>().apply {
//        repeat(1) {
//            add(Note(it, "Title: $it", "Content: $it", System.currentTimeMillis(), false))
//        }
//    }
//
//    private val notesListFlow = MutableStateFlow<List<Note>>(testData.toList())
//
//    override suspend fun addNote(
//        title: String,
//        content: String,
//        isPinned: Boolean,
//        updatedAt: Long
//    ) {
//        notesListFlow.update {  oldList ->
//            val note = Note(
//                id =  oldList.size,
//                title = title,
//                content = content,
//                updatedAt = System.currentTimeMillis(),
//                isPinned = false
//            )
//            oldList + note
//        }
//    }
//
//    override suspend fun deleteNote(noteId: Int) {
//        notesListFlow.update { oldList ->
//            oldList.toMutableList().apply {
//                removeIf { it.id == noteId }
//            }
//        }
//    }
//
//    override suspend fun editNote(note: Note) {
//        notesListFlow.update { oldList ->
//            oldList.map {
//                if (it.id == note.id) {
//                    note
//                } else {
//                    it
//                }
//            }
//        }
//    }
//
//    override fun getAllNotes(): Flow<List<Note>> {
//        return notesListFlow.asStateFlow()
//    }
//
//    override suspend fun getNode(noteId: Int): Note {
//        return notesListFlow.value.first { it.id == noteId }
//    }
//
//    override fun searchNotes(query: String): Flow<List<Note>> {
//        return notesListFlow.map { currentList ->
//            currentList.filter {
//                it.title.contains(query) || it.content.contains(query)
//            }
//        }
//    }
//
//
//    override suspend fun switchPinnedStatus(noteId: Int) {
//        notesListFlow.update { oldList ->
//            oldList.map {
//                if (it.id == noteId) {
//                    it.copy(isPinned = !it.isPinned)
//                } else {
//                    it
//                }
//            }
//        }
//    }
//}