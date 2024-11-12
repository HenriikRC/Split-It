package sdu.splitit.views.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import sdu.splitit.model.User

@Composable
fun UserAvatar(user: User) {
    val painter = if (user.avatar != null) {
        rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatar)
                .size(Size.ORIGINAL)
                .build(),
//            placeholder = painterResource(R.drawable.placeholder), // Optional
//            error = painterResource(R.drawable.error), // Optional
            contentScale = ContentScale.Crop
        )
    } else {
        null // No image available, we'll use initials
    }

    if (painter != null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(38.dp) // Outer size including the border
                .shadow(8.dp, CircleShape, spotColor = Color.Black, ambientColor = Color.Black)
                .clip(CircleShape)
                .background(Color.Gray) // Border color
        ) {
            Image(
                painter = painter,
                contentDescription = "${user.firstName}'s avatar",
                modifier = Modifier
                    .size(36.dp) // Inner size, slightly smaller than outer
                    .clip(CircleShape) // Ensures the image remains circular
                    .background(MaterialTheme.colorScheme.tertiary) // Background color inside the border
            )
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(38.dp) // Outer size including the border
                .shadow(8.dp, CircleShape, spotColor = Color.Black, ambientColor = Color.Black)
                .clip(CircleShape)
                .background(Color.Gray) // Border color
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp) // Inner size, slightly smaller than outer
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onSecondary)
            ) {
                Text(
                    text = "${user.firstName.first()}${user.lastName.first()}",
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }

}

@Composable
fun GroupCard(group: Group, user: User) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background( MaterialTheme.colorScheme.tertiary )
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

//            Text(
//                text = group.members.joinToString(", ", "Group members: ", ".") { it.firstName },
//                fontSize = 16.sp,
//                fontFamily = FontFamily.SansSerif
//            )

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
