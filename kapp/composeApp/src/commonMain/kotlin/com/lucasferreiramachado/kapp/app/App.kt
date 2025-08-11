package com.lucasferreiramachado.kapp.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.app.coordinators.app.AppCoordinator
import com.lucasferreiramachado.kapp.app.coordinators.app.AppCoordinatorAction
import com.lucasferreiramachado.kapp.app.coordinators.app.AppNavigationRoute

@Composable
fun App(
    startDestination: Any = AppNavigationRoute.SplashScreen,
    initialAction: AppCoordinatorAction = AppCoordinatorAction.StartLoginFlow,
) {
    MaterialTheme {
        val appCoordinator = AppCoordinator()
        appCoordinator.start(
            startDestination,
            initialAction = initialAction
        )
    }
}