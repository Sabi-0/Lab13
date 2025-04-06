package com.example.lab13  // Asegúrate que coincida con tu paquete real

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {  // Usamos MaterialTheme directamente en lugar de BMICalculatorTheme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMICalculatorApp()
                }
            }
        }
    }
}

@Composable
fun BMICalculatorApp() {
    var weightInput by remember { mutableStateOf("") }
    var heightInput by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf<Double?>(null) }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "BMI Calculator",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = weightInput,
            onValueChange = {
                if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    weightInput = it
                }
            },
            label = { Text("Weight (kg)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = showError && weightInput.isBlank(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (showError && weightInput.isBlank()) {
                    Text(
                        text = "Required",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        OutlinedTextField(
            value = heightInput,
            onValueChange = {
                if (it.isEmpty() || it.matches(Regex("^\\d*\\.?\\d*\$"))) {
                    heightInput = it
                }
            },
            label = { Text("Height (cm)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = showError && heightInput.isBlank(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (showError && heightInput.isBlank()) {
                    Text(
                        text = "Required",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        Button(
            onClick = {
                if (weightInput.isBlank() || heightInput.isBlank()) {
                    showError = true
                    bmiResult = null
                } else {
                    showError = false
                    val weight = weightInput.toDouble()
                    val height = heightInput.toDouble() / 100
                    bmiResult = weight / height.pow(2)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate BMI")
        }

        bmiResult?.let { result ->
            val (category, color) = when {
                result < 18.5 -> Pair("Underweight", MaterialTheme.colorScheme.primary)
                result < 25 -> Pair("Normal weight", MaterialTheme.colorScheme.tertiary)
                result < 30 -> Pair("Overweight", MaterialTheme.colorScheme.error)
                else -> Pair("Obese", MaterialTheme.colorScheme.error)
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = color.copy(alpha = 0.2f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = color
                        )
                        Text(
                            text = "Your BMI",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    Text(
                        text = "%.1f".format(result),
                        style = MaterialTheme.typography.displaySmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = category,
                        style = MaterialTheme.typography.titleMedium,
                        color = color
                    )
                }
            }
        }

        if (showError && (weightInput.isBlank() || heightInput.isBlank())) {
            Text(
                text = "Please enter both weight and height",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BMICalculatorPreview() {
    MaterialTheme {  // Cambiado a MaterialTheme
        BMICalculatorApp()
    }
}