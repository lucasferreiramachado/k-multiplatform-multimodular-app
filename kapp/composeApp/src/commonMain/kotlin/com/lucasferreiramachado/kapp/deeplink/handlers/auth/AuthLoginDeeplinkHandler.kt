package com.lucasferreiramachado.kapp.deeplink.handlers.auth

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorAction
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinator
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinatorAction
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute

class AuthLoginDeeplinkHandler(
    val coordinator: DeeplinkCoordinator
): KDeeplinkHandler {

    override val deeplink: AppDeeplinkRoute = AppDeeplinkRoute.AuthLoginFlow

    override val composable: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit) = {
        LaunchedEffect(deeplink.route) {
            coordinator.handle(
                DeeplinkCoordinatorAction.ProcessDeeplink(
                    deeplink,
                    FeaturesCoordinatorAction.StartLoginFlow
                )
            )
        }
    }
}