package sdu.splitit.viewmodel.authentication

import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sdu.splitit.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage,
) : UserRepository {

    override suspend fun createUser(user: User): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                postgrest.from("users").insert(user)
                true
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getUserById(userId: String): User? {
        return withContext(Dispatchers.IO) {
            postgrest.from("users").select() {
                filter { eq("id",userId) }
            }
        }.decodeSingle<User>()
    }
}