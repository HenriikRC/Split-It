package sdu.splitit.View

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sdu.splitit.viewmodel.AuthState
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun LoginForm(viewModel: AuthViewModel, navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState = viewModel.authstate.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate("home")
            }
            is AuthState.Error -> {
                Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            }
            else -> {

            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Title
        Text(
            text = "Login",
        )

        Column {

            //Email
            Column {
                Text(
                    text = "Email"
                )

                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
            }

            //Password
            Column {
                Text(
                    text = "Password"
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") }
                )
            }

            //Login Button
            FilledTonalButton(
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        return@FilledTonalButton
                    }
                    viewModel.login(email, password)
                    navController.navigate("home")
                }
            ) {
                Text("Login")
            }

            //Go To Register Button
            TextButton(
                onClick = {
                    navController.navigate("register")
                },
                enabled = authState.value != AuthState.Loading
            ) {
                Text("Go To Register")
            }
        }
    }
}