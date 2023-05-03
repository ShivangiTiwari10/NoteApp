package com.example.notesfoxandroid.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesfoxandroid.DataBase.NoteDataBase
import com.example.notesfoxandroid.DataBase.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository

    val allnotes: LiveData<List<Note>>

    init {
        val dao = NoteDataBase.getDataBase(application).getNoteDao()
        repository = NotesRepository(dao)
        allnotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun upDateNotes(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

}