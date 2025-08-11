package com.lucasferreiramachado.kapp.product.list.ui.screens.list

sealed class ListUiEvent {
    object BackButtonPressed : ListUiEvent()
    data class ProductItemSelectedAt(val index: Int) : ListUiEvent()
}