package com.lucasferreiramachado.kapp.deeplink.coordinator

import com.lucasferreiramachado.kapp.deeplink.domain.usecases.GetLoggedUserUseCase
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinator

interface DeeplinkCoordinatorFactoryI {
    val getLoggedUserUseCase: GetLoggedUserUseCase

    fun create(parent: FeaturesCoordinator): DeeplinkCoordinator
}
//  = GetLoggedUserUseCase(repository = UserRepositoryFactory.create())