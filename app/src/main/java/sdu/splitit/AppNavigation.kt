package sdu.splitit

import AddExpenseForm
import HomePageForm
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sdu.splitit.viewmodel.AuthViewModel
import sdu.splitit.viewmodel.ExpenseViewModel
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import sdu.splitit.views.authentication.RegisterForm
import sdu.splitit.views.authentication.LoginForm
import sdu.splitit.views.groups.GroupsOverview

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "groupsOverview"
    ) {

        composable("register") {
            RegisterForm(
                viewModel = AuthViewModel(),
                navController = navController
            )
        }

        composable("home") {
            HomePageForm(
                viewModel = AuthViewModel(),
                navController = navController
            )
        }

        composable("login") {
            LoginForm(
                viewModel = AuthViewModel(),
                navController = navController
            )
        }

        composable("groupsOverview") {
            GroupsOverview(
                viewModel = GroupsOverviewViewModel(),
                navController = navController,
                onAddNewGroup = { GroupsOverviewViewModel().addMockGroup() }
            )
        }


        composable("expenseForm") {
            AddExpenseForm(
                viewModel = ExpenseViewModel(),
                groupId = 1,
                navController = navController
            )
        }

    }
}

