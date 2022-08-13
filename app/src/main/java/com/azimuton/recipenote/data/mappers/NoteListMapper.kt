package com.azimuton.recipenote.data.mappers

import com.azimuton.recipenote.data.storage.models.NoteEntity
import com.azimuton.recipenote.domain.models.Note

class NoteListMapper : Mapper<List<NoteEntity>, List<Note>> {

    override fun mapFromEntity(type: List<NoteEntity>): List<Note> {
        val listNote = ArrayList<Note>()

        type.forEach { noteEntity ->
            listNote.add(
                Note(
                    dbnoteid = noteEntity.dbnoteEntityid,
                    dbnotetitle = noteEntity.dbnoteEntitytitle,
                    dbnotecontent = noteEntity.dbnoteEntitycontent,
                    dbnoteimage = noteEntity.dbnoteEntityimage
                )
            )
        }

        return listNote.toList()
    }

    override fun mapToEntity(type: List<Note>): List<NoteEntity> {
        val listNote = ArrayList<NoteEntity>()

        type.forEach { note ->
            listNote.add(
                NoteEntity(
                    dbnoteEntityid = note.dbnoteid,
                    dbnoteEntitytitle = note.dbnotetitle,
                    dbnoteEntitycontent = note.dbnotecontent,
                    dbnoteEntityimage = note.dbnoteimage
                )
            )
        }

        return listNote.toList()
    }
}