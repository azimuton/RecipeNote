package com.azimuton.recipenote.presentation.di

import com.azimuton.recipenote.data.repository.SaveDataNoteRepositoryImpl
import com.azimuton.recipenote.data.storage.NoteStorage
import com.azimuton.recipenote.data.storage.room.NoteStorageRoomImpl
import com.azimuton.recipenote.data.storage.room.dao.NoteDao
import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideNoteStorage(noteDao: NoteDao): NoteStorage {
        return NoteStorageRoomImpl(noteDao = noteDao)
    }

    @Singleton
    @Provides
    fun provideNoteRepository(noteStorage: NoteStorage): SaveDataNoteRepository {
        return  SaveDataNoteRepositoryImpl(noteStorage = noteStorage)
    }
}