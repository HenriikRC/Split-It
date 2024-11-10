package sdu.splitit.viewmodel

import sdu.splitit.model.User

class RegisterViewModel {


    fun registerUser(firstName: String, lastName: String, phoneNumber: String, profilePicture: String?) {
        val user = User(
            id = 1,
            firstName = firstName,
            lastName = lastName,
            phoneNumber = phoneNumber,
            profilePicture = profilePicture ?: "default_profile_picture"
        )

        println("User registered: ${user.firstName} ${user.lastName} with phone number: ${user.phoneNumber}")
    }
}