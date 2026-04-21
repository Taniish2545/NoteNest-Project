package com.example.notenest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notenest.data.Note
import com.example.notenest.data.NoteDao
import com.example.notenest.data.User
import com.example.notenest.data.UserDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteDao: NoteDao,
    private val userDao: UserDao
) : ViewModel() {

    val notes = noteDao.getAllNotes()

    private val _loggedInUser = MutableStateFlow<User?>(null)
    val loggedInUser: StateFlow<User?> = _loggedInUser.asStateFlow()

    fun registerUser(name: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val existingUser = userDao.getUserByEmail(email)
            if (existingUser == null) {
                userDao.registerUser(User(name = name, email = email, password = password))
                onResult(true)
            } else {
                onResult(false)
            }
        }
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.loginUser(email, password)
            _loggedInUser.value = user
            onResult(user != null)
        }
    }

    fun addNote(title: String, content: String, category: String) {
        viewModelScope.launch {
            val note = Note(
                title = title,
                content = content,
                category = category,
                createdAt = "Apr 20"
            )
            noteDao.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.updateNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.deleteNote(note)
        }
    }

    suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)
    }
}