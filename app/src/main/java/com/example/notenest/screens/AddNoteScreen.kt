package com.example.notenest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notenest.viewmodel.NoteViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel,
    onSaveDone: () -> Unit,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Study") }

    val categories = listOf("Work", "Study", "Personals", "Ideas")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← Back")
        }

        Text("Add Note", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = category,
                onValueChange = {},
                readOnly = true,
                label = { Text("Category") },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            category = it
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    viewModel.addNote(title, content, category)
                    onSaveDone()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Note")
        }
    }
}