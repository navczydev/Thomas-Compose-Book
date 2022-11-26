package com.example.thomas_compose_book

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Factorial() {
    var expanded by remember { mutableStateOf(false) }
    var text by remember {
        mutableStateOf(factorialAsString(5))
    }
    Box(
        // modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                expanded = true
            },
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            for (n in 0 until 10) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    text = factorialAsString(n)
                }, text = {
                        Text("$n!", style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue))
                    })
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewFactorial() {
    MaterialTheme {
        Factorial()
    }
}

fun factorialAsString(n: Int): String {
    var result = 1L
    for (i in 1..n) {
        result *= i
    }
    return "$n! = $result"
}
