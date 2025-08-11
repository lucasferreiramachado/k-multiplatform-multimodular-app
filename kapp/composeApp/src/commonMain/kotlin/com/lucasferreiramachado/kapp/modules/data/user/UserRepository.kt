package com.lucasferreiramachado.kapp.data.user

import com.lucasferreiramachado.kapp.data.user.model.AuthenticatedUser

public interface UserRepository {

    public fun authenticate(username: String, password: String): AuthenticatedUser?

    public fun loggedUser(): AuthenticatedUser?
}