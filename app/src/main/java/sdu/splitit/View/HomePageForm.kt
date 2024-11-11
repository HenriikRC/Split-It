package sdu.splitit.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sdu.splitit.viewmodel.AuthState
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun HomePageForm(authViewModel: AuthViewModel, navController: NavController) {

    val authstate = authViewModel.authstate.value

    LaunchedEffect(authstate) {
        when (authstate) {
            is AuthState.Unauthenticated -> {
                navController.navigate("login")
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Home Page")

            Spacer(modifier = Modifier.padding(16.dp))

            Text("Logged in as: ${authViewModel.usersEmail}")

            FilledTonalButton(
                onClick = {
                    authViewModel.logout()
                    navController.navigate("home")
                },
                content = { Text("Logout") }
            )
        }
    }
}