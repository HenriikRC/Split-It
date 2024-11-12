package sdu.splitit.model

data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    var balance: HashMap<Int, Double>, //Int = group ID, Double = balance in that group
    var avatar: String?
)
