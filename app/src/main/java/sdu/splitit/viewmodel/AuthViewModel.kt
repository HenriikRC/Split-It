package sdu.splitit.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import sdu.splitit.model.SupabaseClient

class AuthViewModel : ViewModel() {

    val client = SupabaseClient.client

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
            } catch (e: Exception) {

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

            } catch (e: Exception) {

            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            SupabaseClient.client.auth.signOut()
        }
    }

}
