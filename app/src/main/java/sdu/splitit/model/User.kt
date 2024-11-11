package sdu.splitit.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    var balance: Double = 0.0
)
