package com.lucasferreiramachado.kapp.product.list.ui.screens.detail

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kapp.product.list.ProductListCoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(
    initialState: DetailUiState = DetailUiState(),
    var coordinator: KCoordinator<ProductListCoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<DetailUiState> = _state.asStateFlow()
    
    fun onEvent(event: DetailUiEvent) {
        when (event) {
            is DetailUiEvent.BackButtonPressed -> {
                coordinator?.trigger(ProductListCoordinatorAction.GoBack)
            }
            is DetailUiEvent.BuyButtonPressed -> {
                coordinator?.trigger(ProductListCoordinatorAction.StarBuyProductFlow)
            }
        }
    }
}

