package sdu.splitit

//import AddExpenseForm
import HomePageForm
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sdu.splitit.viewmodel.AuthViewModel
//import sdu.splitit.viewmodel.ExpenseViewModel
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import sdu.splitit.views.authentication.AddRegisterForm
import sdu.splitit.views.authentication.LoginForm
import sdu.splitit.views.groups.GroupsOverview
import sdu.splitit.views.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Profile"
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

        composable("groupsOverview") {
            GroupsOverview(
                viewModel = GroupsOverviewViewModel(),
                NavHostController = navController
            )
        }
        composable("Profile") {
            ProfileScreen(
                NavHostController = navController
            )
        }

        /*
        composable("ExpenseForm") {
            AddExpenseForm(
                viewModel = ExpenseViewModel(),
                groupId = 1,
                NavHostController = navController
            )
        }
         */
    }
}

