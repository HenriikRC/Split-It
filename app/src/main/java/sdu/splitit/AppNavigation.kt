package sdu.splitit

import HomePageForm
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sdu.splitit.view.AddRegisterForm
import sdu.splitit.view.LoginForm
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {

        composable("register") {
            AddRegisterForm(
                viewModel = AuthViewModel(),
                NavHostController = navController
            )
        }

        composable("home") {
            HomePageForm(
                viewModel = AuthViewModel(),
                NavHostController = navController
            )
        }

        composable("login") {
            LoginForm(
                viewModel = AuthViewModel(),
                NavHostController = navController
            )
        }
    }
}
