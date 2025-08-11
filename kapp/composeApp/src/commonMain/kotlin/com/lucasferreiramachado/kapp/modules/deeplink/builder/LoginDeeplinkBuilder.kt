package com.lucasferreiramachado.kapp.deeplink.builder

import com.lucasferreiramachado.kapp.deeplink.AppDeeplink
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute
import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder
import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute

class LoginDeeplinkBuilder: KDeeplinkBuilder {

    override val route: KDeeplinkRoute = AppDeeplinkRoute.AuthLoginFlow

    override fun build(): String {
        return createRoute()
    }

    private fun createRoute(): String {
        val scheme = AppDeeplink.defaultScheme()
        return "${scheme}${route.route}"
    }
}