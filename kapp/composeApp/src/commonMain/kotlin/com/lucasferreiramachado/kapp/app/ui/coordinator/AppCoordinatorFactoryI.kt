package com.lucasferreiramachado.kapp.app.ui.coordinator

import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinatorFactoryI
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorFactoryI

interface AppCoordinatorFactoryI {

    val featuresCoordinatorFactory: FeaturesCoordinatorFactoryI
    val deeplinkCoordinatorFactory: DeeplinkCoordinatorFactoryI

    fun create(): AppCoordinator
}