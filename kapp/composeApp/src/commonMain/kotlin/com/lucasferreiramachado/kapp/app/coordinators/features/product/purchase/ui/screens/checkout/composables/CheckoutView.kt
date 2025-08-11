package com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutUiEvent
import com.lucasferreiramachado.kapp.product.purchase.ui.screens.checkout.CheckoutUiState

@Composable
fun CheckoutView(
    state: CheckoutUiState,
    onEvent: (CheckoutUiEvent) -> Unit
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
                    append("Products -> Purchase")
                },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = buildAnnotatedString {
                    append("Hello, \nwelcome to the checkout page")
                },
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Product:\n")
                    }
                    append(state.product)
                    append("\nPrice: ${state.price}\n")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("\nAddress:\n")
                    }
                    append(state.address)

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("\n\nPayment Method:\n")
                    }
                    append(state.paymentMethod)
                },
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 24.dp, 0.dp, 0.dp)
            )

            OutlinedButton(
                onClick = {
                    onEvent(CheckoutUiEvent.ConfirmButtonPressed)
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Text(text = "Confirm",
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            TextButton(
                onClick = {
                  onEvent(CheckoutUiEvent.BackButtonPressed)
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