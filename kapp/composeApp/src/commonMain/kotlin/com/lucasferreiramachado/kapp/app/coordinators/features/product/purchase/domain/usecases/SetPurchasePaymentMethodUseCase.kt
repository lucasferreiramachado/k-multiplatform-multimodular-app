package com.lucasferreiramachado.kapp.product.purchase.domain.usecases

import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.model.PaymentMethod


class SetPurchasePaymentMethodUseCase(
    val repository: PurchaseRepository
) {
    fun execute(paymentMethod: PaymentMethod) = repository.setPaymentMethod(paymentMethod)
}