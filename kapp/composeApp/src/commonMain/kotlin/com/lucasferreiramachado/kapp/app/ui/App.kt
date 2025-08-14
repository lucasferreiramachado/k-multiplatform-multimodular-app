package com.lucasferreiramachado.kapp.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinatorAction
import com.lucasferreiramachado.kapp.app.ui.navigation.AppNavigationRoute
import com.lucasferreiramachado.kapp.di.AppCoordinatorFactory

@Composable
fun App(
    startDestination: Any = AppNavigationRoute.SplashScreen,
    initialAction: AppCoordinatorAction = AppCoordinatorAction.StartLoginFlow,
) {
    MaterialTheme {
        val appCoordinator = AppCoordinatorFactory().create()
        appCoordinator.start(
            startDestination,
            initialAction = initialAction
        )
    }
}