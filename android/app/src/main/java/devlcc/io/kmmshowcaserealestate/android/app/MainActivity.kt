package devlcc.io.kmmshowcaserealestate.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.android.app.listings.Listings
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme
import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel
import org.koin.android.ext.android.getKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Listings(
                        viewModel = getKoin().get<ListingsViewModel>(),
                        logger = getKoin().get<Logger>(),
                        // modifier = Modifier,
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
