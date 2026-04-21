package com.example.notenest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notenest.data.Note

@Composable
fun HomeScreen(
    notes: List<Note>,
    onAddClick: () -> Unit,
    onNoteClick: (Int) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }

    val categories = listOf("All", "Work", "Study", "Personals", "Ideas")

    val filteredNotes = notes.filter {
        val matchesSearch = it.title.contains(searchText, ignoreCase = true) ||
                it.content.contains(searchText, ignoreCase = true)
        val matchesCategory = selectedCategory == "All" || it.category == selectedCategory
        matchesSearch && matchesCategory
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("NoteNest", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search notes...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                categories.forEach { category ->
                    Text(
                        text = category,
                        modifier = Modifier.clickable { selectedCategory = category }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(filteredNotes) { note ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clickable { onNoteClick(note.noteId) }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Title: ${note.title}")
                            Text("Preview: ${note.content.take(20)}...")
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("[${note.category}]    ${note.createdAt}")
                        }
                    }
                }
            }
        }
    }
}