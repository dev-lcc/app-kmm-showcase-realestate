package devlcc.io.kmmshowcaserealestate.android.app.home.favourites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    // ViewModel
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = "FavouritesScreen"
        )
    }

}


// internal fun FavouritesScreen(
//  // FavouritesUiState
//) {
//
//}
