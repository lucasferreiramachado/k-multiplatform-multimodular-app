package com.lucasferreiramachado.kapp.app.ui.coordinator

import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class AppCoordinatorAction: KCoordinatorAction {

    data object AppInitialized : AppCoordinatorAction()
    data object StartLoginFlow : AppCoordinatorAction()
    data class StartHomeFlow(val username: String) : AppCoordinatorAction()
}
