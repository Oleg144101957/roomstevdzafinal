package com.example.roomstevdzafinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomstevdzafinal.data.NoteRoomDb
import com.example.roomstevdzafinal.models.Note
import com.example.roomstevdzafinal.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val myLiveNotes: LiveData<List<Note>>
    val repo: NoteRepository

    init {
        val noteDao = NoteRoomDb.getNoteDatabase(application).noteDao()
        repo = NoteRepository(noteDao)
        myLiveNotes = repo.readAllData
    }

    fun addNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repo.addNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repo.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteNote(note)
        }
    }

}