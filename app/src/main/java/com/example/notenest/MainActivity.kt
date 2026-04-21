package com.example.notenest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.notenest.navigation.AppNavGraph
import com.example.notenest.ui.theme.NoteNestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteNestTheme {
                AppNavGraph()
            }
        }
    }
}