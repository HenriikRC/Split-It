package sdu.splitit.viewmodel.authentication

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signOut(): Boolean
}