package com.azimuton.recipenote.data.storage.room.dao

import androidx.room.*
import com.azimuton.recipenote.data.storage.models.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAll(): List<NoteEntity>

    @Query("DELETE FROM NoteEntity WHERE dbnoteEntityid IN (SELECT dbnoteEntityimage FROM NoteEntity)")
    fun delImage()

    @Insert
    fun insertNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM NoteEntity WHERE dbnoteEntityid = :id")
    fun getNoteById(id: Int): NoteEntity?
}
