package com.azimuton.recipenote.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val dbnoteEntityid: Int = 0,
    @ColumnInfo
    val dbnoteEntitytitle: String,
    @ColumnInfo
    val dbnoteEntitycontent: String,
    @ColumnInfo
    val dbnoteEntityimage: String,

)
//{
//    @PrimaryKey(autoGenerate = true)
//    var dbnoteEntityid: Int = 0
//}