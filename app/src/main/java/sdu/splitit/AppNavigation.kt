package sdu.splitit

import AddExpenseForm
import HomePageForm
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

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val groupsOverviewViewModel = GroupsOverviewViewModel() // Reference to access groups

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
                viewModel = groupsOverviewViewModel,
                navController = navController,
                onAddNewGroup = { groupsOverviewViewModel.addMockGroup() }

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

        composable("expenseForm") {
            val groups = mutableListOf<Group>()
            val sampleUsers = listOf(
                User(1, "Henrik", "Christensen", hashMapOf(), ""),
                User(2, "Morten", "Andersen", hashMapOf(), ""),
                User(3, "Andreas", "Honoré", hashMapOf(), ""),
                User(4, "Mathias", "Sundby", hashMapOf(), ""),
                User(5, "Lasse", "Hvilsted", hashMapOf(), ""),
                User(6, "Marcus", "Ellested", hashMapOf(), "")
            )
            groups.add(Group(id = 1, name = "Hackermen", members = sampleUsers, expenses = mutableListOf()))
            AddExpenseForm(
                viewModel = ExpenseViewModel(),
                group = groups[0],
                NavHostController = navController

            )
        }
    }
}
