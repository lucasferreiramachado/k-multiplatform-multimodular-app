package com.lucasferreiramachado.kapp.app.ui.screens.splash.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kapp.home.compose.app.ui.screens.splash.SplashUiEvent
import com.lucasferreiramachado.kapp.home.compose.app.ui.screens.splash.SplashUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SplashScreenPreview(
    state: SplashUiState = SplashUiState(),
    events: List<SplashUiEvent> = emptyList()
) {
    previewSplashScreen(state, events)
}

@Composable
fun SplashView(
    state: SplashUiState,
    onEvent: (SplashUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center)
        ) {
            Text(text = state.title,
                modifier = Modifier.fillMaxWidth().padding(),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.primary,
            )

            Text(text = state.subtitle,
                modifier = Modifier.fillMaxWidth().padding(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}