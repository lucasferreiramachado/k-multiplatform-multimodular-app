package com.lucasferreiramachado.kapp.data.purchase

import com.lucasferreiramachado.kapp.data.purchase.model.PaymentMethod
import com.lucasferreiramachado.kapp.data.purchase.model.Purchase
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct

interface PurchaseRepository {
    fun startNewPurchase(product: ShoppingCartProduct)
    fun setAddress(address: String)
    fun setPaymentMethod(paymentMethod: PaymentMethod)
    fun getCurrentPurchase(): Purchase?
}