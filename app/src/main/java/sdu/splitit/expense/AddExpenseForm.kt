import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import sdu.splitit.model.User
import sdu.splitit.ui.theme.*
import sdu.splitit.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseForm(viewModel: ExpenseViewModel, groupId: Int) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedPayer by remember { mutableStateOf<User?>(null) }
    var splitEvenly by remember { mutableStateOf(true) }
    val group = viewModel.getGroupById(groupId)
    val groupMembers = group?.members ?: listOf()

    val participantSelection = remember { mutableStateListOf(*Array(groupMembers.size) { true }) }
    var participantDropdownExpanded by remember { mutableStateOf(false) }

    val selectedCount = participantSelection.count { it }
    val participantLabel = "Participants ($selectedCount/${groupMembers.size})"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Add New Expense",
                style = MaterialTheme.typography.titleLarge,
                color = DarkGreen,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Expense Title", color = Color.Gray) },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Teal,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Teal,
                    unfocusedLabelColor = DarkGreen,
                    cursorColor = DarkGreen
                ),
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount", color = Color.Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Teal,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedLabelColor = Teal,
                    unfocusedLabelColor = DarkGreen,
                    cursorColor = DarkGreen
                ),
                modifier = Modifier.fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedPayer?.let { "${it.firstName} ${it.lastName}" } ?: "Select Payer",
                    onValueChange = {},
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Teal,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Teal,
                        unfocusedLabelColor = DarkGreen,
                        cursorColor = DarkGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    label = { Text("Payer", color = Color.Gray) }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    groupMembers.forEachIndexed { index, user ->
                        DropdownMenuItem(
                            text = { Text(text = "${user.firstName} ${user.lastName}", color = DarkGreen) },
                            onClick = {
                                selectedPayer = user
                                expanded = false
                            }
                        )
                    }
                }
            }

            ExposedDropdownMenuBox(
                expanded = participantDropdownExpanded,
                onExpandedChange = { participantDropdownExpanded = !participantDropdownExpanded }
            ) {
                TextField(
                    value = participantLabel,
                    onValueChange = {},
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Teal,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Teal,
                        unfocusedLabelColor = DarkGreen,
                        cursorColor = DarkGreen
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    label = { Text("Participants", color = Color.Gray) }
                )
                ExposedDropdownMenu(
                    expanded = participantDropdownExpanded,
                    onDismissRequest = { participantDropdownExpanded = false }
                ) {
                    groupMembers.forEachIndexed { index, user ->
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = participantSelection[index],
                                onCheckedChange = { isChecked -> participantSelection[index] = isChecked },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = LimeGreen,
                                    uncheckedColor = Color.Gray
                                )
                            )
                            Text("${user.firstName} ${user.lastName}", color = DarkGreen)
                        }
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Split Evenly", style = MaterialTheme.typography.bodyLarge, color = DarkGreen)
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = splitEvenly,
                    onCheckedChange = { splitEvenly = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = LimeGreen,
                        checkedTrackColor = DarkGreen,
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }

            Button(
                onClick = {
                    if (selectedPayer != null && title.isNotBlank() && amount.toDoubleOrNull() != null) {
                        val selectedParticipants = groupMembers.filterIndexed { index, _ -> participantSelection[index] }
                        viewModel.addExpense(
                            groupId = groupId,
                            title = title,
                            amount = amount.toDouble(),
                            payer = selectedPayer!!,
                            participants = selectedParticipants,
                            isSplitEvenly = splitEvenly
                        )
                        title = ""
                        amount = ""
                        selectedPayer = null
                        splitEvenly = true
                        participantSelection.fill(true)
                    } else {
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Teal)
            ) {
                Text("Add Expense", color = White)
            }
        }
    }
}
