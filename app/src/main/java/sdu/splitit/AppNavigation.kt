package sdu.splitit

import AddExpenseForm
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import sdu.splitit.model.Group
import sdu.splitit.model.User
import sdu.splitit.viewmodel.AuthViewModel
import sdu.splitit.viewmodel.ExpenseViewModel
import sdu.splitit.viewmodel.GroupDetailsViewModel
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import sdu.splitit.views.authentication.RegisterForm
import sdu.splitit.views.authentication.LoginForm
import sdu.splitit.views.group.GroupDetails
import sdu.splitit.views.groups.GroupsOverview
import sdu.splitit.views.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val groupsOverviewViewModel = GroupsOverviewViewModel() // Reference to access groups

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("register") {
            RegisterForm(
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
                viewModel = groupsOverviewViewModel,
                navController = navController,
                onAddNewGroup = { groupsOverviewViewModel.addMockGroup() }

            )
        }
        composable("profile") {
            ProfileScreen(
                NavHostController = navController
            )
        }

        composable("groupDetails/{groupId}") { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")?.toIntOrNull()
            val group = groupsOverviewViewModel.groups.find { it.id == groupId }
            group?.let {
                val groupDetailsViewModel = GroupDetailsViewModel(group = it)
                GroupDetails(
                    group = groupDetailsViewModel.group,
                    viewModel = groupDetailsViewModel,
                    navController = navController
                )
            }
        }

        composable("addExpense/{groupId}") { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")?.toIntOrNull()
            val group = groupsOverviewViewModel.groups.find { it.id == groupId }

            if (group != null) {
                AddExpenseForm(
                    viewModel = ExpenseViewModel(),
                    group = group,
                    navController = navController

                )
            }
        }
    }
}
