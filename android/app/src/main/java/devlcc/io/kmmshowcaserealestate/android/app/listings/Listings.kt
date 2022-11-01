package devlcc.io.kmmshowcaserealestate.android.app.listings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel

@Composable
fun Listings(
    viewModel: ListingsViewModel,
    logger: Logger,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareFlow = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val uiState by lifecycleAwareFlow.collectAsState(initial = viewModel.uiState.value)

    PropertyLabel(
        which = Property(
            propertyID = "M9941116325",
            listingID = "2946805109",
            propType = Property.Type.Condo,
            propSubType = Property.SubType.Condos,
            price = 2_500_000,
        )
    )

}

@Composable
fun PropertyLabel(which: Property) {
    Text(text = which.toString().replace(",", ",\n"))
}