package com.lucasferreiramachado.kapp.app.ui.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kapp.app.ui.navigation.splashNavigation
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.compose.external.listenExternalUriDeeplink

class AppCoordinator(
    val factory: AppCoordinatorFactoryI,
    override val parent: KCoordinator<*>? = null
) : RootComposeKCoordinator<AppCoordinatorAction> {

    private var navHostController: NavHostController? = null
    private var initialAction: AppCoordinatorAction? = null
    private var featuresCoordinator = factory.featuresCoordinatorFactory.create(parent = this)
    private var deeplinkCoordinator = factory.deeplinkCoordinatorFactory.create(parent = featuresCoordinator)

    override fun handle(action: AppCoordinatorAction) {
        when (action) {
            is AppCoordinatorAction.StartLoginFlow -> {
                featuresCoordinator.trigger(FeaturesCoordinatorAction.StartLoginFlow)
            }
            is AppCoordinatorAction.StartHomeFlow -> {
                val username = action.username
                featuresCoordinator.trigger(FeaturesCoordinatorAction.StartHomeFlow(username = username))
            }
            is AppCoordinatorAction.AppInitialized -> {
                navHostController?.popBackStack()
                initialAction?.let { trigger(it) }
            }
        }
    }

    override fun setupNavigation(
        initialAction: AppCoordinatorAction,
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        this.initialAction = initialAction
        this.navHostController = navHostController

        featuresCoordinator.setupNavigation(navGraphBuilder, navHostController)
        deeplinkCoordinator.setupNavigation(navGraphBuilder, navHostController)

        navGraphBuilder.splashNavigation(this)
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

