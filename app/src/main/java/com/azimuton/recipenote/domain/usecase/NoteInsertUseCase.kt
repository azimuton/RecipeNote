package com.azimuton.recipenote.domain.usecase

import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository

class NoteInsertUseCase( private val saveDataNoteRepository: SaveDataNoteRepository) {
    fun execute(note: Note){
        return saveDataNoteRepository.insertNote(note = note)
    }
}