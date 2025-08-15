package com.lucasferreiramachado.kapp.features.coordinator

import com.lucasferreiramachado.kcoordinator.KCoordinator

interface FeaturesCoordinatorFactoryI {

//    val productsCoordinatorFactory: ProductsCoordinatorFactoryI

    fun create(parent: KCoordinator<*>): FeaturesCoordinator
}