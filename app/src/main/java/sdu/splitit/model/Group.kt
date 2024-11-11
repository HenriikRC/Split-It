package sdu.splitit.model

data class Group(
    val id: Int,
    val name: String,
    val members: List<User>,
    val expenses: MutableList<Expense>
)