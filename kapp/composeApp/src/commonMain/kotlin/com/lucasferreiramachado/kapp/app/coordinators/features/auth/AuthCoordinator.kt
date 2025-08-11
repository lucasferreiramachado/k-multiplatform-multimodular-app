package com.lucasferreiramachado.kapp.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kapp.auth.flow.login.ui.screens.login.composables.LoginScreen
import com.lucasferreiramachado.kapp.auth.login.ui.screens.login.LoginViewModel
import kotlinx.serialization.Serializable

sealed class AuthNavigationRoute {
    @Serializable data object LoginScreen: AuthNavigationRoute()
}

sealed class AuthCoordinatorAction: KCoordinatorAction {
    data class StartLogin(val onAuthenticated: OnAuthenticated) : AuthCoordinatorAction()
    data class Authenticated(val username: String) : AuthCoordinatorAction()
    data object GoBack : AuthCoordinatorAction()
}

fun interface OnAuthenticated {
    fun send(username: String)
}

class AuthCoordinator(
    override val parent: KCoordinator<*>,
) : ComposeKCoordinator<AuthCoordinatorAction> {

    private var onAuthenticated: OnAuthenticated? = null
    private var navHostController: NavHostController? = null

    override fun handle(action: AuthCoordinatorAction) {
        when (action) {
            is AuthCoordinatorAction.StartLogin -> {
                onAuthenticated = action.onAuthenticated
                navHostController?.navigate(AuthNavigationRoute.LoginScreen)
            }
            is AuthCoordinatorAction.Authenticated -> {
                val username = action.username
                navHostController?.popBackStack()
                onAuthenticated?.send(username)
            }
            is AuthCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<AuthNavigationRoute.LoginScreen> {
                val viewModel = LoginViewModel(coordinator = this@AuthCoordinator)
                LoginScreen(viewModel)
            }
        }
    }
}