package sdu.splitit.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun LoginForm(viewModel: AuthViewModel, NavHostController: NavController) {
    Box(

    ) {
        Column {
            Text("Login")

            FilledTonalButton(
                onClick = {

                },
                content = { Text("Login") }
                
            )
        }
    }
}