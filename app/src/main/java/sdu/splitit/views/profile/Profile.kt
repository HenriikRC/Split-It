package sdu.splitit.views.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import sdu.splitit.R
import sdu.splitit.ui.theme.Gray
import sdu.splitit.viewmodel.AuthViewModel


@Composable
fun ProfileScreen(viewModel: AuthViewModel, NavHostController: NavHostController) {

    val userFirstName = viewModel.userFirstName
    val userLastName = viewModel.userLastName
    val userEmail = viewModel.userEmail
    val userImageUri = viewModel.userImageUri

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC))
            .verticalScroll(rememberScrollState())

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.wave),
                contentDescription = "Wave background",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            userImageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }
            /*
            Image(
                painter = painterResource(id = R.drawable.icon_information),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(100.dp)
                    .align(Alignment.Center)
            )
            */
        }



        Spacer(modifier = Modifier.height(16.dp))

        Text(
            fontSize = 25.sp,
            text = "$userFirstName $userLastName",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = userEmail,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))

        // Account Options
        AccountOptionButton(
            iconId = R.drawable.icon_payment,
            text = "Payment Methods",
            onClick = { /* Handle click */ },
        )

        AccountOptionButton(
            iconId = R.drawable.icon_information,
            text = "Profile Information",
            onClick = { NavHostController.navigate("ProfileInformation") }
        )

        AccountOptionButton(
            iconId = R.drawable.icon_friends,
            text = "Friends & Groups",
            onClick = { /* Handle click */ }
        )

        AccountOptionButton(
            iconId = R.drawable.icon_history,
            text = "History",
            onClick = { /* Handle click */ }
        )

        AccountOptionButton(
            iconId = R.drawable.icon_notifications,
            text = "Notifications",
            onClick = { /* Handle click */ }
        )
    }
}

@Composable
fun AccountOptionButton(iconId: Int, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Gray),
        contentPadding = PaddingValues(16.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = text,
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 70.dp)  // Adds padding to bring text closer to the center
            )
        }
    }
}


