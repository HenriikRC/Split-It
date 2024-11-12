package sdu.splitit.viewmodel.authentication

import sdu.splitit.model.User

interface UserRepository  {
    suspend fun createUser(user: User): Boolean
    suspend fun getUserById(userId: String): User?
}