package com.lucasferreiramachado.kapp.auth.flow.login.domain.usecases

class ValidateLoginInputUseCase {

    fun execute(username: String, password: String): ValidateLoginInputResult {
        val usernameError = ValidateUsernameUseCase().execute(username)
        val isUsernameValid = usernameError == null

        val passwordError = ValidatePasswordUseCase().execute(password)
        val isPasswordValid = passwordError == null

        return ValidateLoginInputResult(
            isValid = isUsernameValid && isPasswordValid,
            usernameError = usernameError,
            passwordError = passwordError
        )
    }
}

data class ValidateLoginInputResult(
    val isValid: Boolean,
    val usernameError: String? = null,
    val passwordError: String? = null,
)