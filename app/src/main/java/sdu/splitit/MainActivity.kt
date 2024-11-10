package sdu.splitit

import AddExpenseForm
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sdu.splitit.register.AddRegisterForm
import sdu.splitit.viewmodel.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = RegisterViewModel()
            AddRegisterForm(viewModel = viewModel)
        }
    }
}
