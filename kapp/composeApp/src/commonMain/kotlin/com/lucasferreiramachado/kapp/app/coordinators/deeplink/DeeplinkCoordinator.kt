package com.lucasferreiramachado.kapp.app.coordinators.deeplink

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.app.coordinators.features.FeaturesCoordinator
import com.lucasferreiramachado.kapp.app.coordinators.features.FeaturesCoordinatorAction
import com.lucasferreiramachado.kapp.app.coordinators.deeplink.domain.usecases.GetLoggedUserUseCase
import com.lucasferreiramachado.kapp.app.coordinators.deeplink.handlers.auth.AuthLoginDeeplinkHandler
import com.lucasferreiramachado.kapp.app.coordinators.deeplink.handlers.products.ProductDetailsByProductDeeplinkHandler
import com.lucasferreiramachado.kapp.app.coordinators.deeplink.handlers.products.ProductsDeeplinkHandler
import com.lucasferreiramachado.kapp.di.UserRepositoryFactory
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute

sealed class DeeplinkCoordinatorAction : KCoordinatorAction {

    data class ProcessDeeplink(val deeplink: AppDeeplinkRoute, val runAction: FeaturesCoordinatorAction): DeeplinkCoordinatorAction()
}

class DeeplinkCoordinator(
    override val parent: FeaturesCoordinator,
    val getLoggedUserUseCase: GetLoggedUserUseCase = GetLoggedUserUseCase(repository = UserRepositoryFactory.create())
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
                val isNotLogged = getLoggedUserUseCase.execute() == null
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