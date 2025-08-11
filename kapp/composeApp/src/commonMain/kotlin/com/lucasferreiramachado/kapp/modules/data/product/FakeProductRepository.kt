package com.lucasferreiramachado.kapp.data.product

import com.lucasferreiramachado.kapp.data.product.model.Product

class FakeProductRepository(): ProductRepository {

    override fun getProducts(): List<Product> {
        var list = mutableListOf<Product>()
        repeat(5) { index ->
            val id = index + 1
            list.add(
                Product(
                    id = id,
                    name = "Product $id",
                    price = id * 4.90
                )
            )
        }
        return list
    }
}