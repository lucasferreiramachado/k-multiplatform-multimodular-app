package com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kapp.product.purchase.PurchaseProductCoordinatorAction
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.GetCurrentPurchaseUseCase
import com.lucasferreiramachado.kapp.di.PurchaseRepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CheckoutViewModel(
    initialState: CheckoutUiState = CheckoutUiState(),
    val coordinator: KCoordinator<PurchaseProductCoordinatorAction>? = null,
    val getCurrentPurchaseUseCase: GetCurrentPurchaseUseCase = GetCurrentPurchaseUseCase(
        PurchaseRepositoryFactory.create()
    )
) : ViewModel() {
    private val _state: MutableStateFlow<CheckoutUiState> = MutableStateFlow(initialState)
    val state: StateFlow<CheckoutUiState> = _state.asStateFlow()

    init {
        val purchase = getCurrentPurchaseUseCase.execute()
        purchase?.let {
            _state.update { state ->
                CheckoutUiState(
                    product = it.purchasedProduct.name,
                    price = "R$ ${it.purchasedProduct.price}",
                    address = it.address ?: "",
                    paymentMethod = it.paymentMethod?.description ?: "",
                )
            }
        }
    }
    
    fun onEvent(event: CheckoutUiEvent) {
        when (event) {
            is CheckoutUiEvent.BackButtonPressed -> {
                coordinator?.trigger(PurchaseProductCoordinatorAction.GoBack)
            }
            is CheckoutUiEvent.ConfirmButtonPressed -> {
                coordinator?.trigger(PurchaseProductCoordinatorAction.PurchaseRealized)
            }
        }
    }
}

