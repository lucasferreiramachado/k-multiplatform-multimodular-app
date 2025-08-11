package com.lucasferreiramachado.kapp.home.ui.screens.home.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.home.ui.screens.home.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}