import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiCalculatorApp()
        }
    }
}

@Composable
fun BmiCalculatorApp(viewModel: BmiViewModel = viewModel()) {
    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Käyttäjän syöttökentät
        TextField(
            value = viewModel.height.value,
            onValueChange = { viewModel.onHeightChange(it) },
            label = { Text("Pituus (metreinä)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.weight.value,
            onValueChange = { viewModel.onWeightChange(it) },
            label = { Text("Paino (kiloina)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tulostetaan laskettu BMI
        Text(
            text = "BMI: ${String.format("%.2f", viewModel.bmi.value)}",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
