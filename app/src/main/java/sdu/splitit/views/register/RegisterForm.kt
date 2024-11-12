package sdu.splitit.views.register

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import sdu.splitit.viewmodel.RegisterViewModel
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import sdu.splitit.ui.theme.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RegisterForm(viewModel: RegisterViewModel) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var selectedImgUri by remember { mutableStateOf<Uri?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        //Title
        Text(
            text = "Register New User",
            style = MaterialTheme.typography.titleLarge,
            color = GreenPrimary,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 72.dp)
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //First Name
            Column {
                Text(
                    text = "First Name",
                    style = MaterialTheme.typography.titleLarge,
                    color = GreenPrimary,
                )

                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                )
            }


            //Last Name
            Column {
                Text(
                    text = "Last Name",
                    style = MaterialTheme.typography.titleLarge,
                    color = GreenPrimary
                )

                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                )
            }


            //Phone Number
            Column {
                Text(
                    text = "Phone Number",
                    style = MaterialTheme.typography.titleLarge,
                    color = GreenPrimary
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Phone Number", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            var photoPickerLauncher = rememberLauncherForActivityResult (
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { uri ->
                    selectedImgUri = uri
                }
            )

            //Image
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Text(
                        text = "Profile Picture",
                        style = MaterialTheme.typography.titleLarge,
                        color = GreenPrimary
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Button(
                        onClick = {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(Teal)
                    ) {
                        Text("Select Image")
                    }
                }

                AsyncImage(
                    model = selectedImgUri,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }


        //Register User Button
        FilledTonalButton(
            onClick = {
                //viewModel.registerUser(firstName, lastName)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 72.dp),

            colors = ButtonDefaults.buttonColors(Teal)
        ) {
            Text("Register User")
        }
    }
}
