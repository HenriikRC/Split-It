import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.jan.supabase.auth.auth
import sdu.splitit.ui.theme.GreenPrimary
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun HomePageForm(viewModel: AuthViewModel, NavHostController: NavController) {

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

            Column {
                Text("First Name: ${viewModel.userMetadata.value?.userFirstName}")
                Text("Last Name: ${viewModel.userMetadata.value?.userLastName}")
                Text("Phone Number: ${viewModel.userMetadata.value?.userPhoneNumber}")
                Text("Email: ${viewModel.userMetadata.value?.userEmail}")
            }

            FilledTonalButton(
                onClick = {
                    viewModel.logoutUser()
                    NavHostController.navigate("login")
                },
                content = { Text("Logout") }
            )
        }
    }
}