package devlcc.io.kmmshowcaserealestate.android.app.home.listings.viewall

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ViewAllListingsScreen(
    modifier: Modifier,
    // ViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = "ViewAllListingsScreen"
        )
    }

}


// internal fun ViewAllListingsScreen(
//  // ViewAllListingsUiState
//) {
//
//}