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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sdu.splitit.ui.theme.GreenPrimary
import sdu.splitit.viewmodel.AuthViewModel

@Composable
fun HomePageForm(viewModel: AuthViewModel, NavHostController: NavController) {

    val context = LocalContext.current

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
            FilledTonalButton(
                onClick = {
                    viewModel.logout(context)
                    NavHostController.navigate("login")
                },
                content = { Text("Logout") }
            )
        }
    }
}