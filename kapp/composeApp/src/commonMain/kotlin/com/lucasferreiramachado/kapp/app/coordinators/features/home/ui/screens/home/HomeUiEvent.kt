package com.lucasferreiramachado.kapp.home.ui.screens.home

sealed class HomeUiEvent {
    object SignOutButtonPressed : HomeUiEvent()
    object ProductsButtonPressed : HomeUiEvent()
}