package com.chukmaldin.notes.di

import android.content.Context
import com.chukmaldin.notes.data.ImageFileManager
import com.chukmaldin.notes.data.NotesDao
import com.chukmaldin.notes.data.NotesDatabase
import com.chukmaldin.notes.data.NotesRepositoryImpl
import com.chukmaldin.notes.domain.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindNotesRepository(
        impl: NotesRepositoryImpl
    ): NotesRepository

    companion object {

        @Singleton
        @Provides
        fun provideNotesDatabase(
            @ApplicationContext context: Context
        ): NotesDatabase {
            return NotesDatabase.getInstance(context)
        }

        @Singleton
        @Provides
        fun provideNotesDao(
            notesDatabase: NotesDatabase
        ): NotesDao {
            return notesDatabase.notesDao()
        }

        @Singleton
        @Provides
        fun provideNotesRepositoryImpl(
            notesDao: NotesDao,
            imageFileManager: ImageFileManager
        ): NotesRepositoryImpl {
            return NotesRepositoryImpl.getInstance(notesDao, imageFileManager)
        }
    }
}