package com.example.roomstevdzafinal.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomstevdzafinal.models.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun reaadAllData(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)


}