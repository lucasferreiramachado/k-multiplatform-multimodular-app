package com.lucasferreiramachado.kapp.data.user

import com.lucasferreiramachado.kapp.data.user.model.AuthenticatedUser

public class FakeUserRepository(
    private var authenticatedUser: AuthenticatedUser? = null
): UserRepository {

    public override fun authenticate(
        username: String,
        password: String,
    ): AuthenticatedUser? {
        authenticatedUser = AuthenticatedUser(
            id = "123",
            username = username,
            name = "John"
        )
        return authenticatedUser
    }

    public override fun loggedUser(): AuthenticatedUser? = authenticatedUser
}