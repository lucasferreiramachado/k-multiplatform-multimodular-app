package com.lucasferreiramachado.kapp.app.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.app.ui.screens.splash.SplashUiState
import com.lucasferreiramachado.kapp.app.ui.screens.splash.SplashViewModel
import com.lucasferreiramachado.kapp.app.ui.screens.splash.composables.SplashScreen

fun NavGraphBuilder.splashNavigation(
    coordinator: AppCoordinator,
) {
    composable<AppNavigationRoute.SplashScreen> {
        SplashScreen(
            SplashViewModel(
                SplashUiState(),
                coordinator
            )
        )
    }
}