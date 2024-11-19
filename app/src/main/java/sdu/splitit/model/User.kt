package sdu.splitit.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?,
    var balance: HashMap<Int, Double>?, //Int = group ID, Double = balance in that group
    var avatar: String?
)
