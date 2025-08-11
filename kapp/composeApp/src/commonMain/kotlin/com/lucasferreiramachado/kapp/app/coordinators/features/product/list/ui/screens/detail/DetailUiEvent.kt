package com.lucasferreiramachado.kapp.product.list.ui.screens.detail

sealed class DetailUiEvent {
    object BackButtonPressed : DetailUiEvent()
    object BuyButtonPressed : DetailUiEvent()
}