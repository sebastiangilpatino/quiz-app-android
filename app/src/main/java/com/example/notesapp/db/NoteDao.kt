package com.example.notesapp.db

import androidx.room.*

// Create DAO for each entity, we have one entity. create one NoteDao
// Provide the functionality expects from the DB
// suspend keyword is used in all the functions to run DB queries under Kotlin Coroutine scope
@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM NOTE ORDER BY id DESC")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM NOTE ORDER BY id ASC LIMIT 1")
    suspend fun getOneNote(): Note

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}