package com.azimuton.recipenote.domain.usecase

import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository

class NoteDelImageUseCase( private  val saveDataNoteRepository: SaveDataNoteRepository) {
    fun execute(){
        saveDataNoteRepository.delImage()
    }
}