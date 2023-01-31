package devlcc.io.kmmshowcaserealestate.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.android.app.listings.Listings
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme
import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel
import org.koin.android.ext.android.getKoin

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = stringResource(id = R.string.app_title)) },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.onBackground,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.background,
                    content = { padding ->
                        Listings(
                            viewModel = getKoin().get<ListingsViewModel>(),
                            logger = getKoin().get<Logger>(),
                            modifier = Modifier.padding(padding),
                        )
                    }
                )
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
