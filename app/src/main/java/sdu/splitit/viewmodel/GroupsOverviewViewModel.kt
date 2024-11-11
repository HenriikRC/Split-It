package sdu.splitit.viewmodel

import androidx.lifecycle.ViewModel
import sdu.splitit.model.Group
import sdu.splitit.model.User

class GroupsOverviewViewModel: ViewModel() {
    val groups = mutableListOf<Group>()

    init {
        val sampleUsers = listOf(
            User(1, "Henrik", "Christensen", balance = hashMapOf(
                1 to 200.00,
                2 to 55.00,
                3 to -50.50,
                4 to -422.50
            )),
            User(2, "Morten", "Andersen", balance = hashMapOf(
                1 to 200.00,
                2 to 55.00,
                3 to -50.50,
                4 to -422.50
            )),
            User(3, "Andreas", "Honoré", balance = hashMapOf(
                1 to 200.00,
                2 to 55.00,
                3 to -50.50,
                4 to -422.50
            )),
            User(4, "Mathias", "Sundby", balance = hashMapOf(
                1 to 200.00,
                2 to 55.00,
                3 to -50.50,
                4 to -422.50
            )),
            User(5, "Lasse", "Hvilsted", balance = hashMapOf(
                1 to 200.00,
                2 to 55.00,
                3 to -50.50,
                4 to -422.50
            )),
            User(6, "Marcus", "Ellested", balance = hashMapOf(
                1 to 200.00,
                2 to 55.00,
                3 to -50.50,
                4 to -422.50
            )),

            )
        groups.add(Group(id = 1, name = "Hackermen", members = sampleUsers, expenses = mutableListOf()))

        groups.add(Group(id = 2, name = "Oldboys", members = listOf(sampleUsers[1], sampleUsers[2]), expenses = mutableListOf()))

        groups.add(Group(id = 3, name = "Young Guns", members = listOf(sampleUsers[4], sampleUsers[5]), expenses = mutableListOf()))

        groups.add(Group(id = 4, name = "The Inbetweeners", members = listOf(sampleUsers[0], sampleUsers[3]), expenses = mutableListOf()))
    }





}