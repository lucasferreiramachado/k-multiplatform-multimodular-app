package com.lucasferreiramachado.kapp.product.purchase.domain.usecases

import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.model.Purchase

class GetCurrentPurchaseUseCase(
    val repository: PurchaseRepository
) {
    fun execute(): Purchase? = repository.getCurrentPurchase()
}