package com.lucasferreiramachado.kapp.auth.flow.login.ui.screens.login

sealed class LoginUiEvent {
    data class UsernameChanged(val username: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object PasswordVisibilityChanged : LoginUiEvent()
    object SignInButtonPressed : LoginUiEvent()
}