package com.azimuton.recipenote.domain.repository

import com.azimuton.recipenote.data.storage.models.NoteEntity
import com.azimuton.recipenote.domain.models.Note

interface SaveDataNoteRepository {
    fun getAll(): List<NoteEntity>

    fun delImage()

    fun insertNote(note: Note)

    fun deleteNote(note: Note)

    fun updateNote(note: Note)

    fun getNoteById(id: Int): NoteEntity?
}