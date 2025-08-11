package com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel

@Composable
fun ListScreen(
    viewModel: ListViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}