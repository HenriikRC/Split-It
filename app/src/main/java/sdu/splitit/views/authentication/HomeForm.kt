import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sdu.splitit.model.User
import sdu.splitit.ui.theme.GreenPrimary
import sdu.splitit.viewmodel.AuthViewModel
import sdu.splitit.viewmodel.UserDataViewModel

@Composable
fun HomePageForm(authViewModel: AuthViewModel, userDataViewModel: UserDataViewModel, NavHostController: NavController) {

    val context = LocalContext.current
    val userState = remember { mutableStateOf<User?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val user = userDataViewModel.getCurrentUser()
        userState.value = user
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Home Page",
            style = MaterialTheme.typography.titleLarge,
            color = GreenPrimary,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 72.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            userState.value?.let { user ->
                Text(text = "Name: ${user.firstName} ${user.lastName}")
                Text(text = "Email: ${user.email}")
                Text(text = "Phone: ${user.phone}")

            }
            FilledTonalButton(
                onClick = {
                    authViewModel.logout(context)
                    NavHostController.navigate("login")
                },
                content = { Text("Logout") }
            )
        }
    }
}