package sdu.splitit.viewmodel

import androidx.collection.emptyLongSet
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sdu.splitit.model.SupabaseClient
import sdu.splitit.model.User

class UserDataViewModel : ViewModel() {

    private val client = SupabaseClient.client

    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser: State<User?> = _currentUser

    private val _userById = mutableStateOf<User?>(null)
    val userById: State<User?> = _userById

    suspend fun getCurrentUser() : User? {
        return withContext(Dispatchers.IO) {
            try {
                val userAuthId = client.auth.retrieveUserForCurrentSession(true).id

                if (userAuthId != null) {
                    client.from("users").select {
                        filter { eq("user_auth_id", userAuthId) }
                    }.decodeSingle<User>()
                } else {
                    null
                }
            } catch (e: Exception) {
                println("Error fetching user data: ${e.message}")
            } as User?
        }
    }
}