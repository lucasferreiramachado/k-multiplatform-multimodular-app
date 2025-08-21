package com.lucasferreiramachado.kapp.di

import org.koin.dsl.koinConfiguration

val KoinApp = koinConfiguration {
    modules(
        appModule
    )
}