package com.lucasferreiramachado.kapp.di

import com.lucasferreiramachado.kapp.auth.login.di.authModule
import com.lucasferreiramachado.kapp.home.di.homeModule
import com.lucasferreiramachado.kapp.product.di.productModule
import org.koin.dsl.koinConfiguration

val KoinApp = koinConfiguration {
    modules(
        appModule,
        authModule,
        homeModule,
        productModule
    )
}