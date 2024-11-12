package sdu.splitit.model

data class User(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    var balance: HashMap<Int, Double>, //Int = group ID, Double = balance in that group
    var avatar: String?
)
