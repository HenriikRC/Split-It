package sdu.splitit.viewmodel

import androidx.lifecycle.ViewModel
import sdu.splitit.model.Expense
import sdu.splitit.model.Group
import sdu.splitit.model.User

class ExpenseViewModel: ViewModel() {
    private val groups = mutableListOf<Group>()

    init {
        val sampleUsers = listOf(

            User(1, "Henrik", "Christensen", hashMapOf(),""),
            User(2, "Morten", "Andersen", hashMapOf(),""),
            User(3, "Andreas", "Honoré", hashMapOf(),""),
            User(4, "Mathias", "Sundby", hashMapOf(),""),
            User(5, "Lasse", "Hvilsted", hashMapOf(),""),
            User(6, "Marcus", "Ellested", hashMapOf(),"")
        )
        groups.add(Group(id = 1, name = "Hackermen", members = sampleUsers, expenses = mutableListOf()))
    }

    fun getGroupById(groupId: Int): Group? {
        return groups.find { it.id == groupId }
    }

    fun addExpense(
        groupId: Int,
        title: String,
        amount: Double,
        payer: User,
        participants: List<User>,
        isSplitEvenly: Boolean
    ) {
        val group = groups.find { it.id == groupId } ?: return
        val expense = Expense(
            id = generateExpenseId(),
            title = title,
            amount = amount,
            payer = payer,
            participants = participants,
            isSplitEvenly = isSplitEvenly
        )
        group.expenses.add(expense)
        updateBalances(group, expense)

        println("Udgifter for '${group.name}':")
        group.expenses.forEach { exp ->
            println(" - ${exp.title}, beløb: ${exp.amount}, betalt af: ${exp.payer.firstName} ${exp.payer.lastName}")
        }
        group.members.forEach { mem ->
            println("${mem.firstName} ${mem.lastName}, saldo: ${mem.balance} DKK")
        }
    }

    private fun updateBalances(group: Group, expense: Expense) {
        val splitAmount = if (expense.isSplitEvenly) {
            expense.amount / expense.participants.size
        } else {
            expense.amount / expense.participants.size
        }
        expense.participants.forEach { participant ->
            val participantBalance = participant.balance.getOrDefault(group.id, 0.0)
            participant.balance[group.id] = participantBalance - splitAmount
        }

        val payerBalance = expense.payer.balance.getOrDefault(group.id, 0.0)
        expense.payer.balance[group.id] = payerBalance + (splitAmount * expense.participants.size)
    }

    fun getBalances(groupId: Int): Map<User, Double> {
        val group = groups.find { it.id == groupId } ?: return emptyMap()
        return group.members.associateWith { it.balance[groupId] ?: 0.0 }
    }

    private fun generateExpenseId(): Int {
        return groups.flatMap { it.expenses }.size + 1
    }
}

