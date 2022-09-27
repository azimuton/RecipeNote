package com.azimuton.recipenote.data.repository

import com.azimuton.recipenote.data.mappers.NoteListMapper
import com.azimuton.recipenote.data.mappers.NoteMapper
import com.azimuton.recipenote.data.storage.NoteStorage
import com.azimuton.recipenote.domain.models.Note
import com.azimuton.recipenote.domain.repository.SaveDataNoteRepository

class SaveDataNoteRepositoryImpl(private val noteStorage: NoteStorage): SaveDataNoteRepository {
    override fun getAll(): List<Note> {
        return  NoteListMapper().mapFromEntity(noteStorage.getAll())
    }

    override fun delImage() {
        noteStorage.delImage()
    }

    override fun insertNote(note: Note) {
        return noteStorage.insertNote(noteEntity = NoteMapper().mapToEntity(note))
    }

    override fun deleteNote(note: Note) {
        return noteStorage.deleteNote(noteEntity = NoteMapper().mapToEntity(note))
    }

    override fun updateNote(note: Note) {
        return noteStorage.updateNote(noteEntity = NoteMapper().mapToEntity(note))
    }

    override fun getNoteById(id:Int): Note? {
        return noteStorage.getNoteById(id)?.let { NoteMapper().mapFromEntity(it) }
    }


}