package com.example.notenest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notenest.data.NoteDao
import com.example.notenest.data.UserDao

class NoteViewModelFactory(
    private val noteDao: NoteDao,
    private val userDao: UserDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(noteDao, userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}