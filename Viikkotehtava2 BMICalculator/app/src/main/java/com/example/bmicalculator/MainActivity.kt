package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    private val bmiViewModel: BMIViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorScreen(viewModel = bmiViewModel)
        }
    }
}

@Composable
fun BMICalculatorScreen(viewModel: BMIViewModel) {
    val height = viewModel.height.value
    val weight = viewModel.weight.value
    val bmi = viewModel.bmi.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Syöte pituudelle
        OutlinedTextField(
            value = height,
            onValueChange = { viewModel.onHeightChanged(it) },
            label = { Text("Pituus (cm)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Syöte painolle
        OutlinedTextField(
            value = weight,
            onValueChange = { viewModel.onWeightChanged(it) },
            label = { Text("Paino (kg)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Näytä BMI-tulos
        Text(text = "BMI: $bmi", style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(24.dp))

        // Laske BMI-painike
        Button(
            onClick = { viewModel.calculateBMI() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Laske BMI")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    // Käytä oletus ViewModelia esikatseluun
    BMICalculatorScreen(viewModel = BMIViewModel())
}
