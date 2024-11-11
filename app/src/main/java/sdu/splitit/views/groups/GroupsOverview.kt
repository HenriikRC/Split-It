package sdu.splitit.views.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sdu.splitit.model.Group
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import sdu.splitit.model.User

@Composable
fun GroupCard(group: Group, user: User) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background( MaterialTheme.colorScheme.primary )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = group.name,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif

            )

            Text(
                text = "Balance: " + user.balance[group.id].toString() + " $",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif
            )

            Text(
                text = group.members.joinToString(", ", "Group members: ", ".") { it.firstName },
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif
            )
        }
    }
}

@Composable
fun GroupsOverview(viewModel: GroupsOverviewViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background( MaterialTheme.colorScheme.background )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(viewModel.groups) { group ->
                GroupCard(group = group, user = viewModel.groups[0].members[0])
            }
        }
    }
}
