package sdu.splitit.viewmodel.authentication

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun signOut(): Boolean {
        try {
            auth.signOut()
            return true
        } catch (e: Exception) {
            return false
        }
    }

}
