package sdu.splitit

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sdu.splitit.View.GroupsOverview
import sdu.splitit.View.HomePageForm
import sdu.splitit.View.LoginForm
import sdu.splitit.View.RegisterForm
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun AppNavigation(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home", builder =  {
        composable("login") {
            LoginForm(
                authViewModel,
                navController = navController
            )
        }
        composable("register") {
            RegisterForm(
                authViewModel,
                navController = navController
            )
        }
        composable("home") {
            HomePageForm(
                authViewModel,
                navController = navController
            )
        }
        composable("groupsOverview") {
            GroupsOverview(navController = navController)
        }
    })
}