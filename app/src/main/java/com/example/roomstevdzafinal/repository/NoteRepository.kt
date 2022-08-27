package com.example.roomstevdzafinal.repository

import androidx.lifecycle.LiveData
import com.example.roomstevdzafinal.data.NoteDao
import com.example.roomstevdzafinal.models.Note

class NoteRepository(val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.reaadAllData()

    fun addNote(note: Note){
        noteDao.addNote(note)
    }

    fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }


}