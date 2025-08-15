package com.lucasferreiramachado.kapp.di

import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinatorFactoryI
import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.user.FakeUserRepository
import com.lucasferreiramachado.kapp.data.user.UserRepository
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinator
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinatorFactoryI
import com.lucasferreiramachado.kapp.deeplink.domain.usecases.GetLoggedUserUseCase
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinator
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorFactoryI
import com.lucasferreiramachado.kcoordinator.KCoordinator
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule: Module = module {
    single<UserRepository> { FakeUserRepository() }
    singleOf(::FakeProductRepository) { bind<ProductRepository>() }
    single<PurchaseRepository> { FakePurchaseRepository(null) }

    singleOf(::DeeplinkCoordinatorFactory) { bind<DeeplinkCoordinatorFactoryI>() }
    singleOf(::FeaturesCoordinatorFactory) { bind<FeaturesCoordinatorFactoryI>() }
    singleOf(::AppCoordinatorFactory) { bind<AppCoordinatorFactoryI>() }
}

//val productModule: Module = module {
//    single { FakeProductRepository() }
//    singleOf(::FakePurchaseRepository) { bind<PurchaseRepository>() }
//    singleOf(::ProductListCoordinatorFactory) { bind<ProductListCoordinatorFactoryI>() }
//    singleOf(::ProductsCoordinatorFactory) { bind<ProductsCoordinatorFactoryI>() }
//    singleOf(::PurchaseProductCoordinatorFactory) { bind<PurchaseProductCoordinatorFactoryI>() }
//    single<PurchaseRepository> { FakePurchaseRepository(null) }
//
//    // factory { (parent: KCoordinator<*>) -> ProductsCoordinator(parent) }
//
////    factory { (parent: KCoordinator<*>) -> ProductsCoordinator(parent) }
////    factory { (parent: KCoordinator<*>) -> PurchaseProductCoordinator(parent) }
////    factory { (parent: KCoordinator<*>) -> ProductListCoordinator(parent) }
//}

class AppCoordinatorFactory(
    override val featuresCoordinatorFactory: FeaturesCoordinatorFactoryI,
    override val deeplinkCoordinatorFactory: DeeplinkCoordinatorFactoryI
): AppCoordinatorFactoryI{
    override fun create(): AppCoordinator = AppCoordinator(factory = this)
}


class FeaturesCoordinatorFactory(
//    override val productsCoordinatorFactory: ProductsCoordinatorFactoryI
): FeaturesCoordinatorFactoryI {
    override fun create(parent: KCoordinator<*>): FeaturesCoordinator {
        return FeaturesCoordinator(
            factory = this,
            parent = parent
        )
    }
}

private class DeeplinkCoordinatorFactory(
    repository: UserRepository,
): DeeplinkCoordinatorFactoryI {
    override val getLoggedUserUseCase: GetLoggedUserUseCase = GetLoggedUserUseCase(
        repository
    )
    override fun create(parent: FeaturesCoordinator): DeeplinkCoordinator {
        return DeeplinkCoordinator(
            this,
            parent = parent
        )
    }
}

//class ProductsCoordinatorFactory(
//    override val purchaseProductCoordinatorFactory: PurchaseProductCoordinatorFactoryI,
//    override val productListCoordinatorFactory: ProductListCoordinatorFactoryI,
//) : ProductsCoordinatorFactoryI {
//    override fun create(
//        parent: KCoordinator<*>
//    ): ProductsCoordinator {
//        return ProductsCoordinator(
//            parent = parent,
//            factory = this
//        )
//    }
//}

//private class ProductListCoordinatorFactory(
//    productRepository: ProductRepository
//): ProductListCoordinatorFactoryI {
//
//    override val getProductsUseCase: GetProductsUseCase = GetProductsUseCase(
//        productRepository
//    )
//
//    override fun create(
//        parent: KCoordinator<*>
//    ): ProductListCoordinator = ProductListCoordinator(
//        this,
//        parent
//    )
//}
//
//private class PurchaseProductCoordinatorFactory(
//    purchaseRepository: PurchaseRepository
//): PurchaseProductCoordinatorFactoryI {
//
//    override val setPurchasePaymentMethodUseCase: SetPurchasePaymentMethodUseCase = SetPurchasePaymentMethodUseCase(purchaseRepository)
//    override val setPurchaseAddressUseCase: SetPurchaseAddressUseCase = SetPurchaseAddressUseCase(purchaseRepository)
//    override val getCurrentPurchaseUseCase: GetCurrentPurchaseUseCase = GetCurrentPurchaseUseCase(purchaseRepository)
//    override val startNewPurchaseUseCase: StarNewPurchaseUseCase = StarNewPurchaseUseCase(purchaseRepository)
//
//    override fun create(
//        parent: KCoordinator<*>
//    ): PurchaseProductCoordinator = PurchaseProductCoordinator(
//        factory = this,
//        parent
//    )
//}