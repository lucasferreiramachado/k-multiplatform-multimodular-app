package com.lucasferreiramachado.kapp.product.list.domain.usecases

import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.product.model.Product

class GetProductsUseCase(
    val repository: ProductRepository
) {
    fun execute(): List<Product> = repository.getProducts()
}