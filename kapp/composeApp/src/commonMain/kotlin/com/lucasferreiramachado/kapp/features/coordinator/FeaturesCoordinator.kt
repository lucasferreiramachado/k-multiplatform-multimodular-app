package com.lucasferreiramachado.kapp.features.coordinator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinator
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinatorAction
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kapp.home.coordinator.HomeCoordinator
import com.lucasferreiramachado.kapp.home.coordinator.HomeCoordinatorAction
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class FeaturesCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator <FeaturesCoordinatorAction>, KoinComponent {

    private val authCoordinator: AuthCoordinator by inject { parametersOf(this) }
    private val homeCoordinator: HomeCoordinator by inject { parametersOf(this) }
    private val productsCoordinator: ProductsCoordinator by inject { parametersOf(this) }

    override fun handle(action: FeaturesCoordinatorAction) {
        when (action) {
            is FeaturesCoordinatorAction.StartLoginFlow -> {
                authCoordinator.trigger(
                    AuthCoordinatorAction.StartLogin { username ->
                        trigger(FeaturesCoordinatorAction.StartHomeFlow(username = username))
                    }
                )
            }
            is FeaturesCoordinatorAction.StartHomeFlow -> {
                val username = action.username
                homeCoordinator.trigger(HomeCoordinatorAction.ShowHomeScreen(username = username))
            }
            is FeaturesCoordinatorAction.StartProductListFlow -> {
                productsCoordinator.trigger(ProductsCoordinatorAction.StartProductList)
            }
            is FeaturesCoordinatorAction.ProductDetailFlow -> {
                productsCoordinator.trigger(ProductsCoordinatorAction.StartProductDetail(
                    com.lucasferreiramachado.kapp.data.product.model.Product(
                    action.product.id,
                    name = action.product.name,
                    price = action.product.price
                )))
            }
            is FeaturesCoordinatorAction.BuyProductFlow -> {
                productsCoordinator.trigger(ProductsCoordinatorAction.StartPurchaseProduct(ShoppingCartProduct(
                    action.product.id,
                    name = action.product.name,
                    price = action.product.price
                )))
            }

            is FeaturesCoordinatorAction.AuthenticateUserAndTriggerAction -> {
                authCoordinator.trigger(
                    AuthCoordinatorAction.StartLogin {
                        trigger(action)
                    }
                )
            }
        }
    }

    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        authCoordinator.setupNavigation(navGraphBuilder, navHostController)
        homeCoordinator.setupNavigation(navGraphBuilder, navHostController)
        productsCoordinator.setupNavigation(navGraphBuilder, navHostController)
    }
}
