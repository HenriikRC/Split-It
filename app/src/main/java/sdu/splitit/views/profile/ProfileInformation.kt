package sdu.splitit.views.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileInformationScreen() {
    var name by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("john.doe@example.com") }
    // Add more state variables as needed

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile Information",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        // Add more TextFields for other profile information

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Handle save action
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}

