import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    // Käyttäjän syöttämät arvot
    var height = mutableStateOf("")
        private set
    var weight = mutableStateOf("")
        private set

    // Laskettu BMI
    var bmi = mutableStateOf(0.0)
        private set

    // Päivitä pituus
    fun onHeightChange(newHeight: String) {
        height.value = newHeight
        calculateBmi()
    }

    // Päivitä paino
    fun onWeightChange(newWeight: String) {
        weight.value = newWeight
        calculateBmi()
    }

    // Yksityinen metodi BMI:n laskemiseksi
    private fun calculateBmi() {
        val heightValue = height.value.toDoubleOrNull()
        val weightValue = weight.value.toDoubleOrNull()

        if (heightValue != null && weightValue != null && heightValue > 0) {
            bmi.value = weightValue / (heightValue * heightValue)
        } else {
            bmi.value = 0.0
        }
    }
}
