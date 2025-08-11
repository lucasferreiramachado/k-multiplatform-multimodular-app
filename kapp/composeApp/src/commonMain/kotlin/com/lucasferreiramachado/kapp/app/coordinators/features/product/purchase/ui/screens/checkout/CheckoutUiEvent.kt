package com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout

sealed class CheckoutUiEvent {
    object BackButtonPressed : CheckoutUiEvent()
    object ConfirmButtonPressed : CheckoutUiEvent()
}