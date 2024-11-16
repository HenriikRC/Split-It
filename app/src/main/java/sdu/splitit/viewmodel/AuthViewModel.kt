package sdu.splitit.viewmodel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import sdu.splitit.model.SharedPreferenceHelper
import sdu.splitit.model.SupabaseClient
import sdu.splitit.model.UserState


class AuthViewModel : ViewModel() {

    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState
    private val client = SupabaseClient.client

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String,
        userFirstName: String,
        userLastName: String,
        userPhone: String,
    ) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {

                val result = client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject {
                        put("first_name", userFirstName)
                        put("last_name", userLastName)
                    }
                }

                val userId = result?.id

                val response = client.postgrest["users"].insert(
                    buildJsonObject {
                        put("id", userId)
                        put("first_name", userFirstName)
                        put("last_name", userLastName)
                        put("phone", userPhone)
                        put("balance", "{}")
                    }
                )
                saveToken(context)
                _userState.value = UserState.Success("Registered user successfully")
                println("___________________User registered successfully___________________")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }

        }
    }


    fun signIn(
        context: Context,
        userEmail: String,
        userPassword: String
    ) {
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                client.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Logged in successfully")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun logout(context: Context) {
        val sharedPref = SharedPreferenceHelper(context)
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.auth.signOut()
                sharedPref.clearPreferences()
                _userState.value = UserState.Success("Logged out successfully")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = client.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            if (accessToken != null) {
                sharedPref.saveStringData("ACCESS_TOKEN", accessToken)
            }
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("ACCESS_TOKEN")
    }


    private val _isUserLoggedIn = mutableStateOf<Boolean>(false)
    val isUserLoggedIn: State<Boolean> = _isUserLoggedIn

    fun isUserLoggedIn(context: Context) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val token = getToken(context)
                if (token.isNullOrEmpty()) {
                    _userState.value = UserState.Error("User not logged in")
                } else {
                    client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User is already logged in")
                    _isUserLoggedIn.value = true
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
                _isUserLoggedIn.value = false
            }
        }
    }
}
