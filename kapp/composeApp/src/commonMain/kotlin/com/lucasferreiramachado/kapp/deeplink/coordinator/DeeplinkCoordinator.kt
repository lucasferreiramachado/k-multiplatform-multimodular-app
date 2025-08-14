package com.lucasferreiramachado.kapp.deeplink.coordinator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.deeplink.handlers.auth.AuthLoginDeeplinkHandler
import com.lucasferreiramachado.kapp.deeplink.handlers.products.ProductDetailsByProductDeeplinkHandler
import com.lucasferreiramachado.kapp.deeplink.handlers.products.ProductsDeeplinkHandler
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinator
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler

class DeeplinkCoordinator(
    val factory: DeeplinkCoordinatorFactoryI,
    override val parent: FeaturesCoordinator,
) : ComposeKCoordinator<DeeplinkCoordinatorAction> {
    private val deeplinkHandlers: List<KDeeplinkHandler> = listOf<KDeeplinkHandler>(
        ProductDetailsByProductDeeplinkHandler(this),
        ProductsDeeplinkHandler(this),
        AuthLoginDeeplinkHandler(this),
    )
    private var featuresCoordinator = parent
    private var navHostController: NavHostController? = null

    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController
        deeplinkHandlers.forEach { it.setupNavigation(navGraphBuilder) }
    }

    override fun handle(action: DeeplinkCoordinatorAction) {
        navHostController?.popBackStack()
        when (action) {
            is DeeplinkCoordinatorAction.ProcessDeeplink -> {
                val isNotLogged = factory.getLoggedUserUseCase.execute() == null
                if (action.deeplink.needsAuthentication.and(isNotLogged)) {
                    featuresCoordinator.trigger(
                        FeaturesCoordinatorAction.AuthenticateUserAndTriggerAction(action.runAction))
                } else {
                    featuresCoordinator.trigger(action.runAction)
                }
            }
        }
    }
}