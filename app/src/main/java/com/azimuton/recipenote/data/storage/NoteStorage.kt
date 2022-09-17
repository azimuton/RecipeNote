package com.azimuton.recipenote.data.storage

import com.azimuton.recipenote.data.storage.models.NoteEntity
import com.azimuton.recipenote.domain.models.Note

interface NoteStorage {

    fun getAll(): List<NoteEntity>

    fun delImage()

    fun insertNote(noteEntity: NoteEntity)

    fun deleteNote(noteEntity: NoteEntity)

    fun updateNote(noteEntity: NoteEntity)

//    fun getNoteById(id: Int): NoteEntity?
}