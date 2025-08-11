package com.lucasferreiramachado.kapp.product.purchase

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.AddressViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.address.composables.AddressScreen
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.composables.Checkout
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.PaymentViewModel
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.payment.composables.PaymentScreen

import com.lucasferreiramachado.kapp.di.PurchaseRepositoryFactory
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import kotlinx.serialization.Serializable

sealed class PurchaseProductNavigationRoute {
    @Serializable object Address: PurchaseProductNavigationRoute()
    @Serializable object Payment: PurchaseProductNavigationRoute()
    @Serializable object Checkout: PurchaseProductNavigationRoute()
}

sealed class PurchaseProductCoordinatorAction: KCoordinatorAction {

    data class StartPurchase(val product: ShoppingCartProduct): PurchaseProductCoordinatorAction()

    data object ShowAddress: PurchaseProductCoordinatorAction()
    data object ShowPayment : PurchaseProductCoordinatorAction()
    data object ShowCheckout : PurchaseProductCoordinatorAction()

    data object PurchaseRealized : PurchaseProductCoordinatorAction()

    data object GoBack : PurchaseProductCoordinatorAction()
}

class PurchaseProductCoordinator(
    override val parent: KCoordinator<*>,
    val startNewPurchaseUseCase: StarNewPurchaseUseCase = StarNewPurchaseUseCase(
        PurchaseRepositoryFactory.create()
    ),
) : ComposeKCoordinator<PurchaseProductCoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: PurchaseProductCoordinatorAction) {
        when (action) {
            is PurchaseProductCoordinatorAction.ShowAddress -> {
                navHostController?.navigate(PurchaseProductNavigationRoute.Address)
            }
            PurchaseProductCoordinatorAction.ShowPayment -> {
                navHostController?.navigate(PurchaseProductNavigationRoute.Payment)
            }
            is PurchaseProductCoordinatorAction.ShowCheckout -> {
                navHostController?.navigate(PurchaseProductNavigationRoute.Checkout)
            }
            is PurchaseProductCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
            is PurchaseProductCoordinatorAction.PurchaseRealized -> {
                val firstRoute = PurchaseProductNavigationRoute.Address
                navHostController?.popBackStack(
                    route = firstRoute,
                    inclusive = true
                )
            }
            is PurchaseProductCoordinatorAction.StartPurchase -> {
                startNewPurchaseUseCase.execute(
                    action.product
                )
                handle(PurchaseProductCoordinatorAction.ShowAddress)
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<PurchaseProductNavigationRoute.Address> {
                val viewModel = AddressViewModel(coordinator = this@PurchaseProductCoordinator)
                AddressScreen(viewModel)
            }

            composable<PurchaseProductNavigationRoute.Payment> {
                val viewModel = PaymentViewModel(coordinator = this@PurchaseProductCoordinator)
                PaymentScreen(viewModel)
            }

            composable<PurchaseProductNavigationRoute.Checkout> {
                val viewModel = CheckoutViewModel(coordinator = this@PurchaseProductCoordinator)
                Checkout(viewModel)
            }
        }
    }
}