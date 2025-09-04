package com.lucasferreiramachado.kapp.features.coordinator

import com.lucasferreiramachado.kapp.deeplink.model.Product
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class FeaturesCoordinatorAction: KCoordinatorAction {
    data object StartLoginFlow : FeaturesCoordinatorAction()
    data class StartHomeFlow(val username: String) : FeaturesCoordinatorAction()
    data object StartProductListFlow : FeaturesCoordinatorAction()
    data class AuthenticateUserAndTriggerAction(val action: FeaturesCoordinatorAction) : FeaturesCoordinatorAction()
    data class ProductDetailFlow(val product: Product) : FeaturesCoordinatorAction()
    data class BuyProductFlow(val product: Product) : FeaturesCoordinatorAction()
}