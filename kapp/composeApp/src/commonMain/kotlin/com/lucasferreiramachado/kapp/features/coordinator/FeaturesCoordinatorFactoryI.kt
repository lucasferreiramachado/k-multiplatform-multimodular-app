package com.lucasferreiramachado.kapp.features.coordinator

import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinatorFactoryI
import com.lucasferreiramachado.kapp.home.coordinator.HomeCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorFactoryI
import com.lucasferreiramachado.kcoordinator.KCoordinator

interface FeaturesCoordinatorFactoryI {

    val authCoordinatorFactory: AuthCoordinatorFactoryI
    val homeCoordinatorFactory: HomeCoordinatorFactoryI
    val productsCoordinatorFactory: ProductsCoordinatorFactoryI

    fun create(parent: KCoordinator<*>): FeaturesCoordinator
}