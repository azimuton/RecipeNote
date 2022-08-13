package com.azimuton.recipenote.data.storage.room

import com.azimuton.recipenote.data.storage.NoteStorage
import com.azimuton.recipenote.data.storage.models.NoteEntity
import com.azimuton.recipenote.data.storage.room.dao.NoteDao
import com.azimuton.recipenote.domain.models.Note

class NoteStorageRoomImpl(private val noteDao: NoteDao): NoteStorage {
    override fun getAll(): List<NoteEntity> {
        return  noteDao.getAll()
    }

    override fun delImage() {
       noteDao.delImage()
    }

    override fun insertNote(noteEntity: NoteEntity) {
        noteDao.insertNote(noteEntity = noteEntity)
    }

    override fun deleteNote(noteEntity: NoteEntity) {
       noteDao.deleteNote(noteEntity = noteEntity)
    }

    override fun updateNote(noteEntity: NoteEntity) {
        noteDao.updateNote(noteEntity = noteEntity)
    }

    override fun getNoteById(id: Int): NoteEntity? {
       return noteDao.getNoteById(id = id)
    }
}