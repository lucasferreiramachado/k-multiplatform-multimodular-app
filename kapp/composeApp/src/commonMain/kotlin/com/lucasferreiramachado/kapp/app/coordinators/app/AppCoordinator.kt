package com.lucasferreiramachado.kapp.app.coordinators.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kapp.app.coordinators.features.FeaturesCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import com.lucasferreiramachado.kapp.app.coordinators.app.ui.screens.SplashScreen
import com.lucasferreiramachado.kapp.di.DeeplinkCoordinatorFactory
import com.lucasferreiramachado.kapp.di.FeaturesCoordinatorFactory
import com.lucasferreiramachado.kdeeplink.compose.external.listenExternalUriDeeplink
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

sealed class AppNavigationRoute {
    @Serializable data object SplashScreen: AppNavigationRoute()
}

sealed class AppCoordinatorAction: KCoordinatorAction {
    data object StartLoginFlow : AppCoordinatorAction()
    data class StartHomeFlow(val username: String) : AppCoordinatorAction()
}

class AppCoordinator(
    featuresCoordinatorFactory: FeaturesCoordinatorFactory = FeaturesCoordinatorFactory(),
    deeplinkCoordinatorFactory: DeeplinkCoordinatorFactory = DeeplinkCoordinatorFactory(),
    override val parent: KCoordinator<*>? = null
) : RootComposeKCoordinator<AppCoordinatorAction> {


    private var featuresCoordinator = featuresCoordinatorFactory.create(parent = this)
    private var deeplinkCoordinator = deeplinkCoordinatorFactory.create(parent = featuresCoordinator)


    override fun handle(action: AppCoordinatorAction) {
        when (action) {
            is AppCoordinatorAction.StartLoginFlow -> {
                featuresCoordinator.trigger(FeaturesCoordinatorAction.StartLoginFlow)
            }
            is AppCoordinatorAction.StartHomeFlow -> {
                val username = action.username
                featuresCoordinator.trigger(FeaturesCoordinatorAction.StartHomeFlow(username = username))
            }
        }
    }

    override fun setupNavigation(
        initialAction: AppCoordinatorAction,
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        featuresCoordinator.setupNavigation(navGraphBuilder, navHostController)
        deeplinkCoordinator.setupNavigation(navGraphBuilder, navHostController)

        navGraphBuilder.composable<AppNavigationRoute.SplashScreen>() {
            SplashScreen(
                onSplashScreenLaunched = {
                    delay(1500)
                    navHostController.popBackStack()
                    trigger(initialAction)
                }
            )
        }
    }

    @Composable
    override fun start(startDestination: Any, initialAction: AppCoordinatorAction) {
        val navHostController = rememberNavController()

        navHostController.listenExternalUriDeeplink()

        NavHost(
            startDestination = startDestination,
            navController = navHostController
        ) {
            setupNavigation(
                initialAction,
                this,
                navHostController
            )
        }
    }
}

