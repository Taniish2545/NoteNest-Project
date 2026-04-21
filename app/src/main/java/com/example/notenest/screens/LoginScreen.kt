package com.example.notenest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notenest.viewmodel.NoteViewModel

@Composable
fun LoginScreen(
    viewModel: NoteViewModel,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome Back", fontSize = 30.sp)

        Spacer(modifier = Modifier.height(24.dp))

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
                viewModel.loginUser(email, password) { success ->
                    if (success) {
                        onLoginSuccess()
                    } else {
                        error = "Invalid email or password"
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }

        TextButton(
            onClick = onRegisterClick,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Don't have an account? Register")
        }
    }
}