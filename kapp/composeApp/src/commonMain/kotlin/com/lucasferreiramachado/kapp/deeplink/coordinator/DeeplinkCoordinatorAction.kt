package com.lucasferreiramachado.kapp.deeplink.coordinator

import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorAction
import com.lucasferreiramachado.kapp.deeplink.route.AppDeeplinkRoute
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction

sealed class DeeplinkCoordinatorAction : KCoordinatorAction {

    data class ProcessDeeplink(val deeplink: AppDeeplinkRoute, val runAction: FeaturesCoordinatorAction): DeeplinkCoordinatorAction()
}