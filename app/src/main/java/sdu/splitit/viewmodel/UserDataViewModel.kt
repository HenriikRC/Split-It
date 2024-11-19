package sdu.splitit.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sdu.splitit.model.SupabaseClient
import sdu.splitit.model.User

class UserDataViewModel : ViewModel() {

    private val client = SupabaseClient.client

    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser: State<User?> = _currentUser

    private val _userById = mutableStateOf<User?>(null)
    val userById: State<User?> = _userById

    fun getCurrentUsersData() {
        viewModelScope.launch(Dispatchers.IO) {
            val userId = client.auth.retrieveUserForCurrentSession(true).id
            println("User ID: $userId")

            if (userId != null) {
                try {
                    val user = client.from("users").select().decodeSingle<User>()
                    _currentUser.value = user

                    println("User first name: ${user.firstName}")
                    println("User last name: ${user.lastName}")
                    println("User phone number: ${user.phone}")
                    println("User email: ${user.email}")

                } catch (e: Exception) {
                    println("Error fetching user data: ${e.message}")
                }
            }
        }
    }
}