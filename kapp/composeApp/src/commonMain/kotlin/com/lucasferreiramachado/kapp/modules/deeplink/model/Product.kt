package com.lucasferreiramachado.kapp.deeplink.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(val id: Int, val name: String, val price: Double)