package com.example.notenest.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.notenest.data.AppDatabase
import com.example.notenest.ui.screens.*
import com.example.notenest.viewmodel.NoteViewModel
import com.example.notenest.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)

    val viewModel: NoteViewModel = viewModel(
        factory = NoteViewModelFactory(database.noteDao(), database.userDao())
    )

    val notes by viewModel.notes.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(
                onContinueClick = {
                    navController.navigate(Routes.Login.route)
                }
            )
        }

        composable(Routes.Login.route) {
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route)
                },
                onRegisterClick = {
                    navController.navigate(Routes.Register.route)
                }
            )
        }

        composable(Routes.Register.route) {
            RegisterScreen(
                viewModel = viewModel,
                onRegisterSuccess = {
                    navController.popBackStack()
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                notes = notes,
                onAddClick = {
                    navController.navigate(Routes.AddNote.route)
                },
                onNoteClick = { id ->
                    navController.navigate(Routes.NoteDetails.createRoute(id))
                }
            )
        }

        composable(Routes.AddNote.route) {
            AddNoteScreen(
                viewModel = viewModel,
                onSaveDone = {
                    navController.popBackStack()
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.NoteDetails.route,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            var note by remember { mutableStateOf<com.example.notenest.data.Note?>(null) }

            LaunchedEffect(noteId) {
                note = viewModel.getNoteById(noteId)
            }

            note?.let {
                NoteDetailsScreen(
                    note = it,
                    onBack = { navController.popBackStack() },
                    onEdit = {
                        navController.navigate(Routes.EditNote.createRoute(it.noteId))
                    },
                    onDelete = {
                        viewModel.deleteNote(it)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            route = Routes.EditNote.route,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getInt("noteId") ?: 0
            var note by remember { mutableStateOf<com.example.notenest.data.Note?>(null) }

            LaunchedEffect(noteId) {
                note = viewModel.getNoteById(noteId)
            }

            note?.let {
                EditNoteScreen(
                    note = it,
                    viewModel = viewModel,
                    onUpdateDone = {
                        navController.popBackStack()
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}