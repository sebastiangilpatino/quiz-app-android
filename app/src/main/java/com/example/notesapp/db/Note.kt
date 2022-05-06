package com.example.notesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/* Annotate as Entity and define columns of the table,
it will define the same name in the table column, if you want to provide the different name use as
@ColumnInfo(name = "note_title")*/
@Entity
data class Note(
    //@ColumnInfo(name = "notetitle")
    val title: String, val note: String, val noteIsTheCorrect: Boolean,
    val note2: String, val note2IsTheCorrect: Boolean,
    val note3: String, val note3IsTheCorrect: Boolean,
    val note4: String, val note4IsTheCorrect: Boolean
) : Serializable
// need to pass Note entity through Fragments. So that entity should be Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
// id is auto generated, so that declaring as instance field not in the constructor