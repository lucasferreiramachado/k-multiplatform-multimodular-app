package com.lucasferreiramachado.kapp.deeplink.route

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.lucasferreiramachado.kapp.deeplink.AppDeeplink
import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute
import kotlinx.serialization.Serializable

@Serializable
sealed class AppDeeplinkRoute(
    override val route: String,
    override val paramName: String = "",
    val needsAuthentication: Boolean = true,
): KDeeplinkRoute {

    @Serializable data object ProductDetailsByProduct : AppDeeplinkRoute("product/detail/{json}", paramName = "json")
    @Serializable data object ProductList : AppDeeplinkRoute("products")

    @Serializable data object AuthLoginFlow : AppDeeplinkRoute("login", needsAuthentication = false)
}

fun AppDeeplinkRoute.navDeeplinks(): List<NavDeepLink> {
    val uriPatterns = AppDeeplink.schemes.map { "$it$route" }
    return uriPatterns.map { navDeepLink { uriPattern = it } }
}