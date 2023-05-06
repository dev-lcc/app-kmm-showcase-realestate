package devlcc.io.kmmshowcaserealestate.android.app.propertydetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PropertyDetailScreen(
    modifier: Modifier = Modifier,
    propertyId: String,
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
            text = "PropertyDetailScreen::[id = $propertyId]"
        )
    }

}


// internal fun PropertyDetailScreen(
//  // PropertyDetailUiState
//) {
//
//}