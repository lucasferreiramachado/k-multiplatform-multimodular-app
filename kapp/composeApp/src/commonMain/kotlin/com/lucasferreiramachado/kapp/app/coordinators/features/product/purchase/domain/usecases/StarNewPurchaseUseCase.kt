package com.lucasferreiramachado.kapp.product.purchase.domain.usecases

import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct

class StarNewPurchaseUseCase(
    val repository: PurchaseRepository
) {
    fun execute(purchasedProduct: ShoppingCartProduct) = repository.startNewPurchase(purchasedProduct)
}