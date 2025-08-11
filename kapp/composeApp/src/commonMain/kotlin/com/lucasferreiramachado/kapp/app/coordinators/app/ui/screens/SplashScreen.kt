package com.lucasferreiramachado.kapp.app.coordinators.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import k_multiplatform_app.kapp.composeapp.generated.resources.Res
import k_multiplatform_app.kapp.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun SplashScreen(
    onSplashScreenLaunched: suspend CoroutineScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "Splash Screen",
            modifier = Modifier.fillMaxWidth().padding(48.dp).align(Alignment.TopCenter),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
        )

        Image(
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.Center),
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = "logo"
        )

        Text(text = "KApp Compose Sample",
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }

    LaunchedEffect(key1 = "splashScreenLaunched") {
        onSplashScreenLaunched()
    }
}