package com.example.notenest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notenest.data.Note

@Composable
fun NoteDetailsScreen(
    note: Note,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()   // 👈 SAFE AREA FIX (important)
            .padding(24.dp)
    ) {

        // 🔹 Top Bar (Back + Title)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Note Details",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Title
        Text(
            text = note.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Category + Date
        Text("[${note.category}]   ${note.createdAt}")

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Content
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = note.content,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 🔹 Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = onEdit) {
                Text("Edit")
            }

            Button(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}