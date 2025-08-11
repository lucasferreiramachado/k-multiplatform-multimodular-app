package com.lucasferreiramachado.kapp.deeplink.builder

import com.lucasferreiramachado.kapp.deeplink.AppDeeplink
import com.lucasferreiramachado.kapp.deeplink.model.Product
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute
import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder
import kotlinx.serialization.json.Json

// DEEPLINK_SCHEME:/product/detail/{json}

class ProductDetailsByProductDeeplinkBuilder(
    val product: Product
): KDeeplinkBuilder {

    override val route: AppDeeplinkRoute = AppDeeplinkRoute.ProductDetailsByProduct

    override fun build(): String {
        val productString = Json.encodeToString(product)
        return createRoute(productString)
    }

    private fun createRoute(json: String): String {
        val deeplinkScheme = AppDeeplink.defaultScheme()
        return "${deeplinkScheme}feature/ProductList/${json}"
    }
}