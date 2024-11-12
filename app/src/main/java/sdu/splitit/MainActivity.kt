package sdu.splitit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sdu.splitit.ui.theme.SplitItTheme
import sdu.splitit.views.profile.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplitItTheme(dynamicColor = false) {
                // Display ProfileScreen here to test it
                ProfileScreen()
            }
        }
    }
}
