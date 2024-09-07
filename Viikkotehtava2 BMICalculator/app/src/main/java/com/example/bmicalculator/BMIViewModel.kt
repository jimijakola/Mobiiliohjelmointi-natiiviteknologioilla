package com.example.bmicalculator

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class BMIViewModel : ViewModel() {

    var height = mutableStateOf("")
        private set
    var weight = mutableStateOf("")
        private set
    var bmi = mutableStateOf("N/A")
        private set

    fun calculateBMI() {
        val heightInCm = height.value.toDoubleOrNull()
        val weightInKg = weight.value.toDoubleOrNull()

        // Muunna pituus metreiksi
        val heightInMeters = heightInCm?.div(100)

        if (heightInMeters != null && weightInKg != null && heightInMeters > 0) {
            val bmiValue = weightInKg / heightInMeters.pow(2)
            bmi.value = String.format("%.2f", bmiValue) // Pyöristetään kahteen desimaaliin
        } else {
            bmi.value = "N/A"
        }
    }

    fun onHeightChanged(newHeight: String) {
        height.value = newHeight
    }

    fun onWeightChanged(newWeight: String) {
        weight.value = newWeight
    }
}
