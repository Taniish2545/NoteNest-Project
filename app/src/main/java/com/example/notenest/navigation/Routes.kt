package com.example.notenest.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object AddNote : Routes("add_note")
    object NoteDetails : Routes("note_details/{noteId}") {
        fun createRoute(noteId: Int) = "note_details/$noteId"
    }
    object EditNote : Routes("edit_note/{noteId}") {
        fun createRoute(noteId: Int) = "edit_note/$noteId"
    }
}