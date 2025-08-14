package com.lucasferreiramachado.kapp.di

import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinator
import com.lucasferreiramachado.kapp.app.ui.coordinator.AppCoordinatorFactoryI
import com.lucasferreiramachado.kapp.auth.login.domain.usecases.AuthenticateUserUseCase
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinator
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinatorFactoryI
import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.user.UserRepository
import com.lucasferreiramachado.kapp.data.user.model.AuthenticatedUser
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinator
import com.lucasferreiramachado.kapp.deeplink.coordinator.DeeplinkCoordinatorFactoryI
import com.lucasferreiramachado.kapp.deeplink.domain.usecases.GetLoggedUserUseCase
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinator
import com.lucasferreiramachado.kapp.features.coordinator.FeaturesCoordinatorFactoryI
import com.lucasferreiramachado.kapp.home.coordinator.HomeCoordinator
import com.lucasferreiramachado.kapp.home.coordinator.HomeCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.GetCurrentPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchasePaymentMethodUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorFactoryI
import com.lucasferreiramachado.kcoordinator.KCoordinator

class AppCoordinatorFactory(
    override val featuresCoordinatorFactory: FeaturesCoordinatorFactoryI = KappFeaturesCoordinatorFactory(),
    override val deeplinkCoordinatorFactory: DeeplinkCoordinatorFactoryI = KappDeeplinkCoordinatorFactory()
): AppCoordinatorFactoryI{

    override fun create(): AppCoordinator = AppCoordinator(factory = this)
}

private class KappFeaturesCoordinatorFactory(
    override val authCoordinatorFactory: AuthCoordinatorFactoryI = KappAuthCoordinatorFactory(),
    override val homeCoordinatorFactory: HomeCoordinatorFactoryI = KappHomeCoordinatorFactory(),
    override val productsCoordinatorFactory: ProductsCoordinatorFactoryI = KappProductsCoordinatorFactory()
): FeaturesCoordinatorFactoryI {
    override fun create(parent: KCoordinator<*>): FeaturesCoordinator {
        return FeaturesCoordinator(
            this,
            parent = parent
        )
    }
}

private class KappDeeplinkCoordinatorFactory(
    override val getLoggedUserUseCase: GetLoggedUserUseCase = GetLoggedUserUseCase(
        KappUserRepositoryFactory.create()
    )
): DeeplinkCoordinatorFactoryI {
    override fun create(parent: FeaturesCoordinator): DeeplinkCoordinator {
        return DeeplinkCoordinator(
            this,
            parent = parent
        )
    }
}

private class KappHomeCoordinatorFactory(): HomeCoordinatorFactoryI {
    override fun create(
        parent: KCoordinator<*>,
    ): HomeCoordinator = HomeCoordinator(
        factory = this,
        parent = parent
    )
}

private class  KappAuthCoordinatorFactory(
    override val authenticateUserUseCase: AuthenticateUserUseCase = AuthenticateUserUseCase(
        repository = KappUserRepositoryFactory.create()
    ),
) : AuthCoordinatorFactoryI {
    override fun create(
        parent: KCoordinator<*>,
    ): AuthCoordinator = AuthCoordinator(
        this,
        parent = parent
    )
}

private object PurchaseRepositoryFactory {
    private val repository: PurchaseRepository = FakePurchaseRepository()

    fun create(): PurchaseRepository {
        return repository
    }
}

private class ProductRepositoryFactory {
    private val repository: ProductRepository = FakeProductRepository()

    fun create(): ProductRepository {
        return repository
    }
}

private class KappProductsCoordinatorFactory(
    override val purchaseProductCoordinatorFactory: PurchaseProductCoordinatorFactoryI = KappPurchaseProductCoordinatorFactory(),
    override val productListCoordinatorFactory: ProductListCoordinatorFactoryI = KappProductListCoordinatorFactory(),
) : ProductsCoordinatorFactoryI {
    override fun create(
        parent: KCoordinator<*>
    ): ProductsCoordinator {
        return ProductsCoordinator(
            parent = parent,
            factory = this
        )
    }
}

private class KappPurchaseProductCoordinatorFactory(
    purchaseRepository: PurchaseRepository = PurchaseRepositoryFactory.create()
): PurchaseProductCoordinatorFactoryI {

    override val setPurchasePaymentMethodUseCase: SetPurchasePaymentMethodUseCase = SetPurchasePaymentMethodUseCase(purchaseRepository)
    override val setPurchaseAddressUseCase: SetPurchaseAddressUseCase = SetPurchaseAddressUseCase(purchaseRepository)
    override val getCurrentPurchaseUseCase: GetCurrentPurchaseUseCase = GetCurrentPurchaseUseCase(purchaseRepository)
    override val startNewPurchaseUseCase: StarNewPurchaseUseCase = StarNewPurchaseUseCase(purchaseRepository)

    override fun create(
        parent: KCoordinator<*>
    ): PurchaseProductCoordinator = PurchaseProductCoordinator(
        factory = this,
        parent
    )
}

private class KappProductListCoordinatorFactory(
    productRepository: ProductRepository = ProductRepositoryFactory().create()
): ProductListCoordinatorFactoryI {

    override val getProductsUseCase: GetProductsUseCase = GetProductsUseCase(
        productRepository
    )

    override fun create(
        parent: KCoordinator<*>
    ): ProductListCoordinator = ProductListCoordinator(
        this,
        parent
    )
}

private object KappUserRepositoryFactory {
    private val repository: UserRepository = KappUserRepository()

    fun create(): UserRepository {
        return repository
    }
}
private class KappUserRepository(
    private var authenticatedUser: AuthenticatedUser? = null
): UserRepository {

    override fun authenticate(
        username: String,
        password: String,
    ): AuthenticatedUser? {
        authenticatedUser = AuthenticatedUser(
            id = "123",
            username = username,
            name = "{user's firstname}"
        )
        return authenticatedUser
    }

    override fun loggedUser(): AuthenticatedUser? = authenticatedUser
}