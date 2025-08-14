package com.lucasferreiramachado.kapp.deeplink.domain.usecases

import com.lucasferreiramachado.kapp.data.user.UserRepository
import com.lucasferreiramachado.kapp.data.user.model.AuthenticatedUser

class GetLoggedUserUseCase(
    val repository: UserRepository
) {
    fun execute(): AuthenticatedUser? = repository.loggedUser()
}