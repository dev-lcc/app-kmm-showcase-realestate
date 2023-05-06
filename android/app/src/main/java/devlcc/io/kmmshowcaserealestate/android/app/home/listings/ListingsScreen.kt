package devlcc.io.kmmshowcaserealestate.android.app.home.listings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.android.app.R
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme
import devlcc.io.kmmshowcaserealestate.core.model.property.Address
import devlcc.io.kmmshowcaserealestate.core.model.property.BuildingSize
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsUiState
import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun ListingsScreen(
    modifier: Modifier = Modifier,
    viewModel: ListingsViewModel = getViewModel(),
    logger: Logger = get(),
    onNavigateToDetail: ((Property) -> Unit) = {},
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareFlow = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    val uiState by lifecycleAwareFlow.collectAsState(initial = viewModel.uiState.value)

    ListingsScreen(
        modifier = modifier,
        state = uiState,
        logger = logger,
        onNavigateToDetail = onNavigateToDetail
    )

}

@Composable
internal fun ListingsScreen(
    modifier: Modifier = Modifier,
    state: ListingsUiState,
    logger: Logger,
    onNavigateToDetail: ((Property) -> Unit) = {},
) {

    val gridState = rememberLazyGridState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            // TODO::
        }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_title)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        content = { padding ->

            Box(
                Modifier
                    .pullRefresh(pullRefreshState)
                    .padding(padding)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = modifier,
                    state = gridState,
                    contentPadding = PaddingValues(4.dp),
                    content = {
                        val allProperties = state.propertiesForRent + state.propertiesForSale + state.propertiesSold

                        items(
                            items = allProperties,
                            key = { it.propertyID ?: "" },
                            span = { GridItemSpan(1) },
                            itemContent = { property ->
                                Box(
                                    Modifier.padding(4.dp)
                                ) {
                                    ListingFeaturedPropertyItem(
                                        which = property,
                                        onClick = { which: Property ->
                                            logger.d("onClick() -> which = $which")
                                            // Navigate to Detail Page
                                            onNavigateToDetail(which)
                                        }
                                    )
                                }
                            }
                        )
                    }
                )

                PullRefreshIndicator(
                    refreshing = state.isLoading,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }

        }
    )

}

internal val SAMPLE_LISTINGS = listOf(
    Property(
        propertyID = "M9941116325",
        listingID = "2946805109",
        propType = Property.Type.Condo,
        propSubType = Property.SubType.Condos,
        price = 2_500_000,
        propStatus = Property.Status.ForSale,
        address = Address(neighborhoodName = "Okinawa Summer House"),
        bathsFull = 4,
        baths = 4,
        beds = 4,
        buildingSize = BuildingSize(size = 4_402, units = "sqft"),
    ),
    Property(
        propertyID = "M9941116326",
        listingID = "2946805110",
        propType = Property.Type.Land,
        propSubType = Property.SubType.Coop,
        price = 2_600_000,
        propStatus = Property.Status.ForRent,
        address = Address(neighborhoodName = "Miami Beach House"),
        bathsFull = 3,
        baths = 3,
        beds = 3,
        buildingSize = BuildingSize(size = 3_301, units = "sqft"),
    ),
    Property(
        propertyID = "M9941116327",
        listingID = "2946805111",
        propType = Property.Type.Apartment,
        propSubType = Property.SubType.Coop,
        price = 2_700_000,
        propStatus = Property.Status.NotForSale,
        address = Address(neighborhoodName = "Florida Grand Party House"),
        bathsFull = 5,
        baths = 5,
        beds = 5,
        buildingSize = BuildingSize(size = 5_501, units = "sqft"),
    ),
    Property(
        propertyID = "M9941116328",
        listingID = "2946805112",
        propType = Property.Type.Condo,
        propSubType = Property.SubType.Coop,
        price = 2_800_000,
        propStatus = Property.Status.ForRent,
        address = Address(neighborhoodName = "Bang Bros Complex Condominium"),
        bathsFull = 3,
        baths = 3,
        beds = 3,
        buildingSize = BuildingSize(size = 2_200, units = "sqft"),
    ),
)

@Preview//(widthDp = 240, heightDp = 480)
@Composable
fun ListingsPreview_Loading() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ListingsScreen(
                state = ListingsUiState(isLoading = true),
                logger = Logger.withTag("ListingsPreview_Loading")
            )
        }
    }
}

@Preview//widthDp = 240, heightDp = 480)
@Composable
fun ListingsPreview_Success() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ListingsScreen(
                state = ListingsUiState(
                    isLoading = false,
                    propertiesForSale = SAMPLE_LISTINGS.filter { it.propStatus == Property.Status.ForSale },
                    propertiesForRent = SAMPLE_LISTINGS.filter { it.propStatus == Property.Status.ForRent },
                    propertiesSold = SAMPLE_LISTINGS.filter { it.propStatus == Property.Status.NotForSale },
                ),
                logger = Logger.withTag("ListingsPreview_Success")
            )
        }
    }
}

@Preview//(widthDp = 240, heightDp = 480)
@Composable
fun ListingsPreview_Refresh() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            ListingsScreen(
                state = ListingsUiState(
                    isLoading = true,
                    propertiesForSale = SAMPLE_LISTINGS.filter { it.propStatus == Property.Status.ForSale },
                    propertiesForRent = SAMPLE_LISTINGS.filter { it.propStatus == Property.Status.ForRent },
                    propertiesSold = SAMPLE_LISTINGS.filter { it.propStatus == Property.Status.NotForSale },
                ),
                logger = Logger.withTag("ListingsPreview_Refresh")
            )
        }
    }
}