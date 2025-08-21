package com.lucasferreiramachado.kapp.di

import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.auth.login.di.authModule
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinator
import com.lucasferreiramachado.kapp.deeplink.domain.usecases.GetLoggedUserUseCase
import com.lucasferreiramachado.kapp.di.modules.dataModule
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinator
import com.lucasferreiramachado.kapp.home.di.homeModule
import com.lucasferreiramachado.kapp.product.di.productsModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule: Module = module {

    includes(dataModule)
    includes(authModule)
    includes(homeModule)
    includes(productsModule)

    singleOf(::GetLoggedUserUseCase)

    single<FeaturesCoordinator> { (parent: AppCoordinator) -> FeaturesCoordinator(parent) }
    single<DeeplinkCoordinator> { (parent: FeaturesCoordinator) -> DeeplinkCoordinator(parent) }
    single<AppCoordinator> { AppCoordinator() }
}