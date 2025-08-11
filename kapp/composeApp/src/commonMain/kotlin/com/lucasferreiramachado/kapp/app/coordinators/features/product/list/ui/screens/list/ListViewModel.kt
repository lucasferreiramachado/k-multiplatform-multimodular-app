package com.lucasferreiramachado.kapp.product.list.ui.screens.list

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kapp.data.product.model.Product
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kapp.product.list.ProductListCoordinatorAction

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel(
    initialState: ListUiState = ListUiState(),
    var coordinator: KCoordinator<ProductListCoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<ListUiState> = _state.asStateFlow()
    
    fun onEvent(event: ListUiEvent) {
        when (event) {
            is ListUiEvent.BackButtonPressed -> {
                coordinator?.trigger(ProductListCoordinatorAction.GoBack)
            }
            is ListUiEvent.ProductItemSelectedAt -> {
                val index = event.index
                val selectedProduct = state.value.products.getOrNull(index)
                selectedProduct?.let {
                    coordinator?.trigger(ProductListCoordinatorAction.ShowDetail(it))
                }
            }
        }
    }
}