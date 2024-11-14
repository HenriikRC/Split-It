package sdu.splitit.views.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
