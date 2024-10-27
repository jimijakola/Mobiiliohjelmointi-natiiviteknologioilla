package com.example.kalorit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.example.kalorit.ui.theme.KaloritTheme

class CalorieScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaloritTheme {
                // Call the main screen composable
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalorieCalculatorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalorieCalculatorScreen(modifier: Modifier = Modifier) {
    // State variables
    var weight by remember { mutableStateOf("") }
    var isMale by remember { mutableStateOf(true) }
    var intensityLevel by remember { mutableStateOf("Matala") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Heading component
        Heading(title = stringResource(id = R.string.app_name))

        // Weight field component
        WeightField(weight = weight, onValueChange = { weight = it })

        // Gender choices component
        GenderChoices(isMale = isMale, onGenderSelected = { isMale = it })

        // Intensity list component
        IntensityList(intensityLevel = intensityLevel, onIntensitySelected = { intensityLevel = it })

        // Button to calculate calorie consumption
        Button(
            onClick = {
                result = calculateCalories(weight.toFloatOrNull() ?: 0f, isMale, intensityLevel)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Laske kalorit")
        }

        // Result display
        Text(text = result, modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun Heading(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}

@Composable
fun WeightField(weight: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = weight,
        onValueChange = onValueChange,
        label = { Text("Paino (kg)") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun GenderChoices(isMale: Boolean, onGenderSelected: (Boolean) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isMale,
            onClick = { onGenderSelected(true) }
        )
        Text("Mies")
        RadioButton(
            selected = !isMale,
            onClick = { onGenderSelected(false) }
        )
        Text("Nainen")
    }
}

@Composable
fun IntensityList(intensityLevel: String, onIntensitySelected: (String) -> Unit) {
    val intensityLevels = listOf("Matala", "Keskiverto", "Korkea")
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = intensityLevel,
            onValueChange = {},
            label = { Text("Aktiivisuustaso") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            intensityLevels.forEach { level ->
                DropdownMenuItem(onClick = {
                    onIntensitySelected(level)
                    expanded = false
                }) {
                    Text(text = level)
                }
            }
        }
    }
}

fun calculateCalories(weight: Float, isMale: Boolean, intensityLevel: String): String {
    // Placeholder for calculation logic
    val baseCalories = if (isMale) 2000 else 1800
    val intensityFactor = when (intensityLevel) {
        "Matala" -> 1.2
        "Keskiverto" -> 1.5
        else -> 1.8
    }
    return "Päivittäinen kalorikulutus: ${(baseCalories * intensityFactor * weight / 70).toInt()} kcal"
}

@Preview(showBackground = true)
@Composable
fun CalorieCalculatorScreenPreview() {
    KaloritTheme {
        CalorieCalculatorScreen()
    }
}
