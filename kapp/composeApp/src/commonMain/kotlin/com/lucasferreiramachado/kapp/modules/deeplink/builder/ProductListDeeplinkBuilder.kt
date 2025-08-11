package com.lucasferreiramachado.kapp.deeplink.builder

import com.lucasferreiramachado.kapp.deeplink.AppDeeplink
import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder
import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute

// DEEPLINK_SCHEME:/products

class ProductListDeeplinkBuilder: KDeeplinkBuilder {

    override val route: KDeeplinkRoute = AppDeeplinkRoute.ProductList

    override fun build(): String {
        return createRoute()
    }

    private fun createRoute(): String {
        val deeplinkScheme = AppDeeplink.defaultScheme()
        return "${deeplinkScheme}${route.route}"
    }
}