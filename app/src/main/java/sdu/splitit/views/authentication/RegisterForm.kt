package sdu.splitit.views.authentication

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import sdu.splitit.viewmodel.AuthViewModel
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import sdu.splitit.ui.theme.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.util.regex.Pattern

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RegisterForm(viewModel: AuthViewModel, navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
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
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            //Email
            Column {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleLarge,
                    color = GreenPrimary
                )

                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = validateEmail(it)
                    },
                    label = { Text("Email", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = emailError.isNotEmpty()
                )

                if (emailError.isNotEmpty()) {
                    Text(
                        text = emailError,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            //Password
            Column {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.titleLarge,
                    color = GreenPrimary
                )

                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = validatePassword(it)
                    },
                    label = { Text("Password", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                if (passwordError.isNotEmpty()) {
                    Text(
                        text = passwordError,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
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
                    modifier = Modifier.size(100.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
            }
        }


        Column(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Register User Button
            FilledTonalButton(
                onClick = {
                    try {
                        navController.navigate("groupsOverview")
                    } catch (e: Exception) {
                        //Toast.makeText(LocalContext.current, "Error registering user", Toast.LENGTH_SHORT).show()
                    }

                },

                colors = ButtonDefaults.buttonColors(Teal)
            ) {
                Text("Register User")
            }

            TextButton(
                onClick = {
                    navController.navigate("login")
                },
            ){
                Text("Already have an account? Login")
            }
        }

    }
}

fun validatePassword(password: String): String {
    val uppercasePattern = Pattern.compile(".*[A-Z].*")
    val digitPattern = Pattern.compile(".*[0-9].*")

    return when {
        password.length < 6 -> "Password must be at least 6 characters long"
        !uppercasePattern.matcher(password)
            .matches() -> "Password must contain at least one uppercase letter"

        !digitPattern.matcher(password)
            .matches() -> "Password must contain at least one numeric character"

        else -> ""
    }
}

fun validateEmail(email: String): String {
    val emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")
    return if (!emailPattern.matcher(email).matches()) {
        "Invalid email address"
    } else {
        ""
    }
}
