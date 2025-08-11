package com.lucasferreiramachado.kapp.product.purchase.ui.screens.address

sealed class AddressUiEvent {
    object BackButtonPressed : AddressUiEvent()
    data class AddressSelected(val address: String) : AddressUiEvent()
}