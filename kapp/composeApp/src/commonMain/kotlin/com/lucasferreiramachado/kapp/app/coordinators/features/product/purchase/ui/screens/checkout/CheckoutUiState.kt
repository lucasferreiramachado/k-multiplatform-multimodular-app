package com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout

data class CheckoutUiState(
    var product: String = "",
    var price: String = "",
    var address: String = "",
    var paymentMethod: String = "",
)