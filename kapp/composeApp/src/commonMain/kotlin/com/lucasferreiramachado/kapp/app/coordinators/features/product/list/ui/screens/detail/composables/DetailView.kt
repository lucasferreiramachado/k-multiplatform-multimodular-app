package com.lucasferreiramachado.kapp.product.list.ui.screens.detail.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiEvent
import com.lucasferreiramachado.kapp.product.list.ui.screens.detail.DetailUiState

@Composable
fun DetailView(
    state: DetailUiState,
    onEvent: (DetailUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
    ) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(40.dp)) {

            Text(
                text = buildAnnotatedString {
                    append("Products -> BuyProduct")
                },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = buildAnnotatedString {
                    append("Hello, \nwelcome to the product detail page")
                },
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            Text(
                text = buildAnnotatedString {
                    append(state.name)
                    append("\nPrice: ${state.price}")
                },
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 24.dp, 0.dp, 0.dp)
            )

            OutlinedButton(
                onClick = {
                    onEvent(DetailUiEvent.BuyButtonPressed)
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 24.dp, 0.dp, 0.dp),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Text(text = "Buy",
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            TextButton(
                onClick = {
                  onEvent(DetailUiEvent.BackButtonPressed)
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp, 0.dp, 0.dp),
            ) {
                Text(text = "Go to back",
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }

        Text(text = "KApp Compose Sample",
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}