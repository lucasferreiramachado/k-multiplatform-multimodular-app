package com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel

@Composable
fun Checkout(
    viewModel: CheckoutViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CheckoutView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}