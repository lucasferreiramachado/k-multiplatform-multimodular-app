package com.lucasferreiramachado.kapp.product.purchase.domain.usecases

import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository

class SetPurchaseAddressUseCase(
    val repository: PurchaseRepository
) {
    fun execute(address: String) = repository.setAddress(address)
}