package com.lucasferreiramachado.kapp.data.product

import com.lucasferreiramachado.kapp.data.product.model.Product

interface ProductRepository {

    fun getProducts(): List<Product>
}