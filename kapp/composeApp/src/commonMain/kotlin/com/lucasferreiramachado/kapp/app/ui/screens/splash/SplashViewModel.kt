package com.lucasferreiramachado.kapp.app.ui.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinatorAction
import com.lucasferreiramachado.kcoordinator.KCoordinator
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    initialState: SplashUiState,
    val coordinator: KCoordinator<AppCoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<SplashUiState> = _state.asStateFlow()

    fun onEvent(event: SplashUiEvent) {
        when (event) {
            is SplashUiEvent.None -> {
                // coordinator?.trigger(someAction)
            }
        }
    }

    init {
        viewModelScope.launch {
            // Configure app settings
            initialize()
            // then call coordinator
            coordinator?.trigger(AppCoordinatorAction.AppInitialized)
        }
    }
    
    private suspend fun initialize() {
        delay(1500)
    }
}

