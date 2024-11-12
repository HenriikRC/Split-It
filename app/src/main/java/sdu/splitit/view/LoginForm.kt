package sdu.splitit.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginForm() {
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