package sdu.splitit

//import AddExpenseForm
import HomePageForm
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sdu.splitit.model.User
import sdu.splitit.viewmodel.AuthViewModel
import sdu.splitit.viewmodel.UserDataViewModel
//import sdu.splitit.viewmodel.ExpenseViewModel
//import sdu.splitit.viewmodel.GroupsOverviewViewModel
import sdu.splitit.views.authentication.AddRegisterForm
import sdu.splitit.views.authentication.LoginForm
//import sdu.splitit.views.groups.GroupsOverview

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("register") {
            AddRegisterForm(
                viewModel = AuthViewModel(),
                NavHostController = navController
            )
        }

        composable("home") {
            HomePageForm(
                authViewModel = AuthViewModel(),
                userDataViewModel = UserDataViewModel(),
                NavHostController = navController
            )
        }

        composable("login") {
            LoginForm(
                viewModel = AuthViewModel(),
                NavHostController = navController
            )
        }

        /*
        composable("groupsOverview") {
            GroupsOverview(
                viewModel = GroupsOverviewViewModel(),
                NavHostController = navController,
                onAddNewGroup = { GroupsOverviewViewModel().addMockGroup() }
            )
        }

         */

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

