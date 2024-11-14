package sdu.splitit.views.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sdu.splitit.model.Group
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import sdu.splitit.model.User
import kotlin.math.min
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import sdu.splitit.R
import sdu.splitit.views.user.ProfileButton
import sdu.splitit.views.user.UserAvatar

@Composable
fun GroupCard(group: Group, user: User, modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background( MaterialTheme.colorScheme.tertiary )
            .clickable {
                navController.navigate("groupDetails")
            }
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

            Row(
                horizontalArrangement = Arrangement.spacedBy((-6).dp),
                modifier = Modifier
                    .padding(top = 8.dp)
            ) {
                group.members.forEach { member ->
                    UserAvatar(member)
                }
            }
        }
    }
}

@Composable
fun GroupsOverview(viewModel: GroupsOverviewViewModel, navController: NavController, onAddNewGroup: () -> Unit) {
    val listState = rememberLazyListState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp // Use device screen height dynamically

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 40.dp, bottom = 80.dp) // Add padding to keep list above the button
    ) {
        Column {
            ProfileButton(user = viewModel.groups[0].members[0], onClick = {
                navController.navigate("profileScreen") // Navigate to the profile screen
            })
            // LazyColumn for group cards with scroll tracking
            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                itemsIndexed(viewModel.groups) { index, group ->
                    // Calculate the vertical offset for each item and adjust opacity
                    val itemOffset = listState.layoutInfo.visibleItemsInfo
                        .firstOrNull { it.index == index }
                        ?.offset ?: 0

                    val opacity = min(1f, 1f - (itemOffset / screenHeight.value))
                    GroupCard(
                        group = group,
                        user = viewModel.groups[0].members[0],
                        modifier = Modifier.graphicsLayer(alpha = opacity),
                        navController = navController
                    )
                }
            }

            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                AddGroupButton(onAddNewGroup = onAddNewGroup)
            }
        }

    }
}

@Composable
fun AddGroupButton(onAddNewGroup: () -> Unit) {
    Button(
        onClick = onAddNewGroup,
        shape = CircleShape, // Make button circular
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,  // Background color
        ),
        modifier = Modifier
            .size(52.dp)
            .shadow(2.dp, CircleShape, spotColor = Color.Black, ambientColor = Color.Black), // Set fixed size to make it a circle
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "Add Group",
            modifier = Modifier.size(42.dp), // Set Icon size as needed without constraint
            tint = MaterialTheme.colorScheme.onSecondary // Adjust tint color if needed
        )
    }
}
