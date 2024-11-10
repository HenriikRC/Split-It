package sdu.splitit.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val profilePicture: String,
    var balance: Double = 0.0
)
