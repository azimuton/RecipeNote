package com.azimuton.recipenote.presentation.di

import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository
import com.azimuton.recipenote.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideNoteDeleteUseCase(noteRepository: SaveDataNoteRepository):NoteDeleteUseCase{
        return  NoteDeleteUseCase(saveDataNoteRepository = noteRepository)
    }
    @Provides
    fun provideNoteDelImageUseCase(noteRepository: SaveDataNoteRepository):NoteDelImageUseCase{
        return  NoteDelImageUseCase(saveDataNoteRepository = noteRepository)
    }
    @Provides
    fun provideNoteGetAllUseCase(noteRepository: SaveDataNoteRepository): NoteGetAllUseCase{
        return NoteGetAllUseCase(saveDataNoteRepository = noteRepository)
    }
    @Provides
    fun provideNoteInsertUseCase(noteRepository: SaveDataNoteRepository): NoteInsertUseCase{
        return  NoteInsertUseCase(saveDataNoteRepository = noteRepository)
    }
    @Provides
    fun provideNoteUpdateUseCase(noteRepository: SaveDataNoteRepository):NoteUpdateUseCase{
        return  NoteUpdateUseCase(saveDataNoteRepository = noteRepository)
    }
    @Provides
    fun provideGetNoteByIdUseCase(noteRepository: SaveDataNoteRepository):NoteGetNoteByIdUseCase{
        return  NoteGetNoteByIdUseCase(saveDataNoteRepository = noteRepository)
    }
}