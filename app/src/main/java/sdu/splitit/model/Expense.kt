package sdu.splitit.model

data class Expense(
    val id: Int,
    val title: String,
    val amount: Double,
    val payer: User,
    val participants: List<User>,
    val isSplitEvenly: Boolean = true
)