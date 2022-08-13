package com.azimuton.recipenote.data.mappers

import com.azimuton.recipenote.data.storage.models.NoteEntity
import com.azimuton.recipenote.domain.models.Note

class NoteMapper : Mapper<NoteEntity, Note> {
    override fun mapFromEntity(type: NoteEntity): Note {
        return Note(dbnotetitle = type.dbnoteEntitytitle,
            dbnotecontent = type.dbnoteEntitycontent,
            dbnoteimage = type.dbnoteEntityimage, dbnoteid = type.dbnoteEntityid)
    }

    override fun mapToEntity(type: Note): NoteEntity {
        return NoteEntity(dbnoteEntitytitle = type.dbnotetitle,
            dbnoteEntitycontent = type.dbnotecontent,
            dbnoteEntityimage = type.dbnoteimage, dbnoteEntityid = type.dbnoteid)
    }
}