package com.lucasferreiramachado.kapp.product.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kapp.data.product.model.Product
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorAction
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiState
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.composables.DetailScreen
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListViewModel
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.composables.ListScreen
import com.lucasferreiramachado.kapp.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kapp.di.ProductRepositoryFactory
import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.screens.list.ListUiState
import kotlinx.serialization.Serializable

sealed class ProductListNavigationRoute {
    @Serializable object List: ProductListNavigationRoute()
    @Serializable data class Detail(val id: Int, val name: String, val price: Double): ProductListNavigationRoute()
}

sealed class ProductListCoordinatorAction: KCoordinatorAction {
    data object ShowList: ProductListCoordinatorAction()
    data class ShowDetail(val product: Product) : ProductListCoordinatorAction()
    data object GoBack : ProductListCoordinatorAction()
    data object StarBuyProductFlow : ProductListCoordinatorAction()
}

class ProductListCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<ProductListCoordinatorAction> {

    private var selectedProduct: Product? = null
    private var navHostController: NavHostController? = null

    override fun handle(action: ProductListCoordinatorAction) {
        when (action) {
            is ProductListCoordinatorAction.ShowList -> {
                navHostController?.navigate(ProductListNavigationRoute.List)
            }
            is ProductListCoordinatorAction.ShowDetail -> {
                selectedProduct = action.product
                navHostController?.navigate(ProductListNavigationRoute.Detail(
                    id = action.product.id,
                    name = action.product.name,
                    price = action.product.price,
                ))
            }
            is ProductListCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }

            is ProductListCoordinatorAction.StarBuyProductFlow -> {
                navHostController?.popBackStack()
                selectedProduct?.let {
                    parent.trigger(ProductsCoordinatorAction.StartPurchaseProduct(
                        ShoppingCartProduct(
                            id = it.id,
                            name = it.name,
                            price = it.price
                        )
                    ))
                }
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<ProductListNavigationRoute.List> {
                val getProductsUseCase: GetProductsUseCase = GetProductsUseCase(
                    ProductRepositoryFactory().create()
                )
                val state = ListUiState(
                    products = getProductsUseCase.execute()
                )
                val viewModel = ListViewModel(
                    initialState = state,
                    coordinator = this@ProductListCoordinator
                )
                ListScreen(viewModel)
            }

            composable<ProductListNavigationRoute.Detail> {

                val route = it.toRoute<ProductListNavigationRoute.Detail>()
                val viewModel = DetailViewModel(
                    initialState = DetailUiState(
                        name = route.name,
                        price = "R$ ${route.price}"
                    ),
                    coordinator = this@ProductListCoordinator
                )
                DetailScreen(viewModel)
            }
        }
    }
}