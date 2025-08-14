package com.lucasferreiramachado.kapp.features.navigation

sealed class FeaturesNavigationRoute(val route: String) {
    object Auth : FeaturesNavigationRoute("auth")
    object Home : FeaturesNavigationRoute("main")
    object Feature : FeaturesNavigationRoute("feature")
}
