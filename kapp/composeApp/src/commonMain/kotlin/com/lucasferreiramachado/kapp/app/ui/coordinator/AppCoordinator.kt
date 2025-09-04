package com.lucasferreiramachado.kapp.app.ui.coordinator

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.lucasferreiramachado.kapp.app.ui.navigation.splashNavigation
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinator
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinator
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.compose.RootComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.compose.external.listenExternalUriDeeplink
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class AppCoordinator(
    override val parent: KCoordinator<*>? = null
) : RootComposeKCoordinator<AppCoordinatorAction>, KoinComponent {

    private var navHostController: NavHostController? = null
    private var initialAction: AppCoordinatorAction? = null
    private val featuresCoordinator: FeaturesCoordinator by inject { parametersOf(this) }
    private val deeplinkCoordinator: DeeplinkCoordinator by inject { parametersOf(featuresCoordinator) }

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

