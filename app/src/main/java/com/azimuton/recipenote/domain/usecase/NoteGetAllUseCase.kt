package com.azimuton.recipenote.domain.usecase

import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository

class NoteGetAllUseCase(private  val saveDataNoteRepository: SaveDataNoteRepository) {
     fun execute(): List<Note>{
         return  saveDataNoteRepository.getAll()
    }
}