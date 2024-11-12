package sdu.splitit.viewmodel

import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import sdu.splitit.model.SupabaseClient

class AuthViewModel : ViewModel() {

    var userFirstName by mutableStateOf("")
    var userLastName by mutableStateOf("")
    var userPhoneNumber by mutableStateOf("")
    var userEmail by mutableStateOf("")
    var userImageUri by mutableStateOf<Uri?>(null)

    fun registerUser(
        userFirstName: String,
        userLastName: String,
        userPhoneNumber: String,
        userEmail: String,
        userPassword: String,
        userImageUri: Uri?
    ) {
        viewModelScope.launch {
            try {
                val auth = SupabaseClient.client.auth

                val signUpResult = auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject {
                        put("first_name", userFirstName)
                        put("last_name", userLastName)
                        put("phone_number", userPhoneNumber)
                        put("profile_picture", userImageUri.toString())
                    }
                }
                // Store the data locally in the ViewModel
                this@AuthViewModel.userFirstName = userFirstName
                this@AuthViewModel.userLastName = userLastName
                this@AuthViewModel.userPhoneNumber = userPhoneNumber
                this@AuthViewModel.userEmail = userEmail
                this@AuthViewModel.userImageUri = userImageUri
            } catch (e: Exception) {

            }
        }
    }
}