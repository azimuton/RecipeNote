package com.azimuton.recipenote.presentation.di

import android.content.Context
import com.azimuton.recipenote.data.storage.room.AppDatabase
import com.azimuton.recipenote.data.storage.room.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return AppDatabase.getDatabase(context = appContext)
    }

    @Singleton
    @Provides
    fun provideNoteDao(roomDatabase : AppDatabase): NoteDao{
        return roomDatabase.noteDao()
    }
}