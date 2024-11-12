package sdu.splitit.viewmodel.authentication

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sdu.splitit.model.User
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    fun registerUser(
        userFirstName: String,
        userLastName: String,
        userPhoneNumber: String,
        userEmail: String,
        userPassword: String,
        userImageUri: String
    ) {
        viewModelScope.launch {
            try {
                val user: User = User(
                    userFirstName,
                    userLastName,
                    userPhoneNumber,
                    userEmail,
                    hashMapOf(),
                    userImageUri
                )

                val signUpResult = authenticationRepository.signUp(
                    email = userEmail,
                    password = userPassword
                )

                if (signUpResult != null) {
                    val userId = 
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
