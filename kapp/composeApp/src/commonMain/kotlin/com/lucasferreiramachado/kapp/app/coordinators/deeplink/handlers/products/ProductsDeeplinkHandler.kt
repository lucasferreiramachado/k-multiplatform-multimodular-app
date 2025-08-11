package com.lucasferreiramachado.kapp.app.coordinators.deeplink.handlers.products

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import com.lucasferreiramachado.kapp.app.coordinators.features.FeaturesCoordinatorAction
import com.lucasferreiramachado.kapp.app.coordinators.deeplink.DeeplinkCoordinator
import com.lucasferreiramachado.kapp.app.coordinators.deeplink.DeeplinkCoordinatorAction
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute

class ProductsDeeplinkHandler(
    val coordinator: DeeplinkCoordinator
): KDeeplinkHandler {

    override val deeplink: AppDeeplinkRoute = AppDeeplinkRoute.ProductList

    override val composable: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit) = {
        LaunchedEffect(deeplink.route) {
            coordinator.handle(
                DeeplinkCoordinatorAction.ProcessDeeplink(
                    deeplink,
                    FeaturesCoordinatorAction.StartProductListFlow
                )
            )
        }
    }
}