package com.lucasferreiramachado.kapp.auth.flow.login.ui.screens.login.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kapp.auth.flow.login.ui.screens.login.LoginUiState
import com.lucasferreiramachado.kapp.auth.flow.login.ui.screens.login.LoginUiEvent
import com.lucasferreiramachado.kapp.auth.login.ui.screens.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}

@Composable
fun previewLoginScreen(
    state: LoginUiState,
    events: List<LoginUiEvent>
) {
    val viewModel = LoginViewModel(state)
    events.forEach {  event -> viewModel.onEvent(event) }
    LoginScreen(
        viewModel
    )
}

@Composable
@Preview
fun LoginScreenPreviewSuccessFlow(
    state: LoginUiState = LoginUiState(),
    events: List<LoginUiEvent> = listOf(
        LoginUiEvent.UsernameChanged("lucasferreiramachado"),
        LoginUiEvent.PasswordChanged("123456"),
        LoginUiEvent.PasswordVisibilityChanged
    )
) {
    previewLoginScreen(state, events)
}

@Composable
@Preview
fun LoginScreenPreviewOnShortUsernameError(
    state: LoginUiState = LoginUiState(),
    events: List<LoginUiEvent> = listOf(
        LoginUiEvent.UsernameChanged(" ")
    )
) {
    previewLoginScreen(state, events)
}

@Composable
@Preview
fun LoginScreenPreviewOnShortPasswordError(
    state: LoginUiState = LoginUiState(),
    events: List<LoginUiEvent> = listOf(
        LoginUiEvent.PasswordChanged("123")
    )
) {
    previewLoginScreen(state, events)
}