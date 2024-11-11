package sdu.splitit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import sdu.splitit.views.groups.GroupsOverview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = GroupsOverviewViewModel()
            GroupsOverview(
                viewModel = viewModel,
            )
        }
    }
}
