package com.lucasferreiramachado.kapp.app.ui.navigation

import kotlinx.serialization.Serializable
sealed class AppNavigationRoute {
    @Serializable data object SplashScreen: AppNavigationRoute()
}
