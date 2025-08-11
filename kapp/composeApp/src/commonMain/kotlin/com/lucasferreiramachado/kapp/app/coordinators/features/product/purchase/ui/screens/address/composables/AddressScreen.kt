package com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel

@Composable
fun AddressScreen(
    viewModel: AddressViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AddressView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}