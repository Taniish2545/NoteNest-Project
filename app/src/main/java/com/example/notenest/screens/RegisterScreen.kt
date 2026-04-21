package com.example.notenest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notenest.viewmodel.NoteViewModel

@Composable
fun RegisterScreen(
    viewModel: NoteViewModel,
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Create Account", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.registerUser(name, email, password) { success ->
                    if (success) {
                        onRegisterSuccess()
                    } else {
                        message = "User already exists"
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (message.isNotEmpty()) {
            Text(message, color = MaterialTheme.colorScheme.error)
        }

        TextButton(
            onClick = onLoginClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Already have an account? Login")
        }
    }
}