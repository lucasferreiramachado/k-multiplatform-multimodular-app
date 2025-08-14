//package com.lucasferreiramachado.kapp.di
//
//import com.lucasferreiramachado.kapp.app.coordinators.features.FeaturesCoordinator
//import com.lucasferreiramachado.kapp.app.coordinators.deeplink.coordinator.DeeplinkCoordinator
//import com.lucasferreiramachado.kapp.auth.AuthCoordinator
//import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
//import com.lucasferreiramachado.kapp.data.product.ProductRepository
//import com.lucasferreiramachado.kapp.home.HomeCoordinator
//import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
//import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
//import com.lucasferreiramachado.kcoordinator.KCoordinator
//import com.lucasferreiramachado.kapp.data.user.FakeUserRepository
//import com.lucasferreiramachado.kapp.data.user.UserRepository
//import com.lucasferreiramachado.kapp.product.ProductsCoordinator
//import com.lucasferreiramachado.kapp.product.list.ProductListCoordinator
//import com.lucasferreiramachado.kapp.product.purchase.PurchaseProductCoordinator
//
//class AuthCoordinatorFactory {
//    fun create(
//        parent: KCoordinator<*>,
//    ): AuthCoordinator = AuthCoordinator(
//        parent
//    )
//}
//
//class HomeCoordinatorFactory {
//    fun create(
//        parent: KCoordinator<*>
//    ): HomeCoordinator = HomeCoordinator(
//        parent
//    )
//}
//
//class ProductsCoordinatorFactory {
//    fun create(parent: KCoordinator<*>): ProductsCoordinator = ProductsCoordinator(parent = parent)
//}
//
//class PurchaseProductCoordinatorFactory {
//    fun create(parent: KCoordinator<*>): PurchaseProductCoordinator = PurchaseProductCoordinator(parent)
//}
//
//class ProductListCoordinatorFactory {
//    fun create(parent: KCoordinator<*>): ProductListCoordinator = ProductListCoordinator(parent)
//}
//
//class FeaturesCoordinatorFactory(
//) {
//    fun create(parent: KCoordinator<*>): FeaturesCoordinator {
//        return FeaturesCoordinator(parent = parent)
//    }
//}
//
//class DeeplinkCoordinatorFactory(
//) {
//    fun create(parent: FeaturesCoordinator): DeeplinkCoordinator {
//        return DeeplinkCoordinator(parent = parent)
//    }
//}
//
//object UserRepositoryFactory {
//    private val repository: UserRepository = FakeUserRepository()
//
//    fun create(): UserRepository {
//        return repository
//    }
//}
//
//object PurchaseRepositoryFactory {
//    private val repository: PurchaseRepository = FakePurchaseRepository()
//
//    fun create(): PurchaseRepository {
//        return repository
//    }
//}
//
//class ProductRepositoryFactory {
//
//    fun create(): ProductRepository = FakeProductRepository()
//}