package com.example.composetiptimesample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetiptimesample.ui.theme.ComposeTipTimeSampleTheme
import java.text.NumberFormat

@Composable
fun TipTimeScreen() {
    Column(
        modifier = Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(
                id = R.string.calculate_tip
            ),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        var amountInput by remember { mutableStateOf("") }
        val amountDouble = amountInput.toDoubleOrNull() ?: 0.0
        val calculateTip = calculateTip(amountDouble)

        TextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(
                        id = R.string.bill_amount
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.tip_amount, calculateTip),
            modifier = Modifier.align(CenterHorizontally),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )


    }
}

private fun calculateTip(amount: Double, percentage: Double = 15.0): String {
    val tip = percentage / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTipTimeSampleTheme {
        TipTimeScreen()
    }
}