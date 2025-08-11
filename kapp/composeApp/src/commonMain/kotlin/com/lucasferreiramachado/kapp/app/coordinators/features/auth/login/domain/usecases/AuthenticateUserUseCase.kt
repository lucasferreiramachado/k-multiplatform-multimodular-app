package com.lucasferreiramachado.kapp.auth.flow.login.domain.usecases

import com.lucasferreiramachado.kapp.data.user.UserRepository
import com.lucasferreiramachado.kapp.data.user.model.AuthenticatedUser

class AuthenticateUserUseCase(
    val repository: UserRepository
) {
    fun execute(username: String, password: String): AuthenticatedUser? = repository.authenticate(username, password)
}