package com.azimuton.recipenote.domain.usecase


import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository

class NoteGetNoteByIdUseCase (private val saveDataNoteRepository: SaveDataNoteRepository) {
     fun execute(id: Int): Note? {
        return saveDataNoteRepository.getNoteById(id = id)
    }
}