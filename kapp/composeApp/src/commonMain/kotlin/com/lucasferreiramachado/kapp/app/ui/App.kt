package com.lucasferreiramachado.kapp.app.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinatorAction
import com.lucasferreiramachado.kapp.app.ui.navigation.AppNavigationRoute
import com.lucasferreiramachado.kapp.di.AppCoordinatorFactory
import com.lucasferreiramachado.kapp.di.KoinApp
import org.koin.compose.KoinMultiplatformApplication
import org.koin.compose.koinInject
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App(
    startDestination: Any = AppNavigationRoute.SplashScreen,
    initialAction: AppCoordinatorAction = AppCoordinatorAction.StartLoginFlow,
) {
    KoinMultiplatformApplication(KoinApp) {
        MaterialTheme {
            val appCoordinator = koinInject<AppCoordinatorFactory>().create()
            appCoordinator.start(
                startDestination,
                initialAction = initialAction
            )
        }
    }
}
