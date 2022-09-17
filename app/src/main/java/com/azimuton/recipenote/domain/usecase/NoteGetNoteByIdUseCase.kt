//package com.azimuton.recipenote.domain.usecase
//
//import com.azimuton.recipenote.data.storage.models.NoteEntity
//import com.azimuton.recipenote.domain.models.Note
//import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository
//
//class NoteGetNoteByIdUseCase (private val saveDataNoteRepository: SaveDataNoteRepository) {
//    suspend fun execute(id: Int): NoteEntity? {
//        return saveDataNoteRepository.getNoteById(id = id)
//    }
//}