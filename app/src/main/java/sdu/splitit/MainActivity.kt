package sdu.splitit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import sdu.splitit.ui.theme.SplitItTheme
import sdu.splitit.viewmodel.GroupsOverviewViewModel
import sdu.splitit.views.groups.GroupsOverview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplitItTheme(dynamicColor = false) {
                val viewModel = GroupsOverviewViewModel()
                GroupsOverview(
                    viewModel = viewModel,
                    onAddNewGroup = { viewModel.addMockGroup() }
                )
            }
        }
    }
}
