package sdu.splitit.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import sdu.splitit.model.SupabaseClient

class AuthViewModel : ViewModel() {

    private val client = SupabaseClient.client

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
                val auth = client.auth

                // Step 1: Sign up the user using Supabase Auth
                val signUpResult = auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }

                // Step 2: If sign-up is successful, store user data in 'users' table
                val userId = signUpResult.user?.id
                if (userId != null) {
                    val postgrest = client.postgrest

                    postgrest["users"].insert(
                        buildJsonObject {
                            put("id", userId)
                            put("first_name", userFirstName)
                            put("last_name", userLastName)
                            put("phone_number", userPhoneNumber)
                            put("email", userEmail)
                            put("profile_picture", userImageUri.toString())
                        }
                    )
                }
            } catch (e: Exception) {
                // Handle the error (e.g., show an error message to the user)
            }
        }
    }

    fun loginUser(
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            try {
                val auth = client.auth

                val signInResult = auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }

                // Handle successful login (e.g., navigate to the home screen)
            } catch (e: Exception) {
                // Handle the error (e.g., show an error message to the user)
            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            SupabaseClient.client.auth.signOut()
        }
    }

}
