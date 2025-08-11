package com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment

sealed class PaymentUiEvent {
    object BackButtonPressed : PaymentUiEvent()
    object CreditCardSelected : PaymentUiEvent()
    object PixSelected : PaymentUiEvent()
}