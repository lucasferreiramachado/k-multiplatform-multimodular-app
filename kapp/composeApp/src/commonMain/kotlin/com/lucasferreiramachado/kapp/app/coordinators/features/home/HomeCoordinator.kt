package com.lucasferreiramachado.kapp.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavUri
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kapp.deeplink.builder.LoginDeeplinkBuilder
import com.lucasferreiramachado.kapp.home.ui.screens.home.HomeUiState
import com.lucasferreiramachado.kapp.home.ui.screens.home.HomeViewModel
import com.lucasferreiramachado.kapp.home.ui.screens.home.composables.HomeScreen
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kapp.deeplink.builder.ProductListDeeplinkBuilder
import kotlinx.serialization.Serializable

sealed class HomeNavigationRoute {
    @Serializable data class HomeScreen(val username: String): HomeNavigationRoute()
}

sealed class HomeCoordinatorAction: KCoordinatorAction {
    data class ShowHomeScreen(val username: String) : HomeCoordinatorAction()
    data object SignOut : HomeCoordinatorAction()
    data object GoBack : HomeCoordinatorAction()
    data object StartProductListFlow : HomeCoordinatorAction()
}

class HomeCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<HomeCoordinatorAction> {

    private var navHostController: NavHostController? = null

    override fun handle(action: HomeCoordinatorAction) {
        when (action) {
            is HomeCoordinatorAction.ShowHomeScreen -> {
                val username = action.username
                navHostController?.popBackStack()
                navHostController?.navigate(HomeNavigationRoute.HomeScreen(username = username))
            }
            is HomeCoordinatorAction.SignOut -> {
                navHostController?.popBackStack()
                val uriString = LoginDeeplinkBuilder().build()
                navHostController?.navigate(NavUri(uriString))
            }
            is HomeCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
            is HomeCoordinatorAction.StartProductListFlow -> {
                val uriString = ProductListDeeplinkBuilder().build()
                navHostController?.navigate(NavUri(uriString))
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<HomeNavigationRoute.HomeScreen> {
                val route = it.toRoute<HomeNavigationRoute.HomeScreen>()

                val initialState = HomeUiState(
                    username = route.username
                )
                val viewModel = HomeViewModel(initialState)
                viewModel.coordinator = this@HomeCoordinator
                HomeScreen(viewModel)
            }
        }
    }
}