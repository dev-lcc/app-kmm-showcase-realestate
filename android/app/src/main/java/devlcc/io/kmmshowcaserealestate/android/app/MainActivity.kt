package devlcc.io.kmmshowcaserealestate.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ContentPreview() {
//    AppTheme {
// TODO:: Provide mock viewModel and logger instance
//    }
//}
