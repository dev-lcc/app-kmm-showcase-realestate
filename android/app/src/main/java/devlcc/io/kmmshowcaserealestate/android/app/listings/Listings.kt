package devlcc.io.kmmshowcaserealestate.android.app.listings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.flowWithLifecycle
import co.touchlab.kermit.Logger
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.component.AppButtonDefaults
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.component.AppFilledButton
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon.AppIcons
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme
import devlcc.io.kmmshowcaserealestate.core.data.repository.PropertiesRepository
import devlcc.io.kmmshowcaserealestate.core.model.property.Address
import devlcc.io.kmmshowcaserealestate.core.model.property.BuildingSize
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import devlcc.io.kmmshowcaserealestate.viewmodel.home.ListingsViewModel
import kotlinx.coroutines.flow.Flow
import java.text.DecimalFormat

@Composable
fun Listings(
    viewModel: ListingsViewModel,
    logger: Logger,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareFlow = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }

    @Suppress("UNUSED_VARIABLE") val uiState by lifecycleAwareFlow.collectAsState(initial = viewModel.uiState.value)

    val gridState = rememberLazyGridState()

    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = modifier,
        state = gridState,
        contentPadding = PaddingValues(4.dp),
        content = {
            val sampleList = listOf(
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
                    propType = Property.Type.MultiFamily,
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
            items(count = sampleList.size,
                key = { sampleList[it].listingID ?: "" },
                span = { GridItemSpan(1) },
                itemContent = {
                    Box(
                        Modifier.padding(4.dp)
                    ) {
                        ListingFeaturedPropertyItem(which = sampleList[it],
                            onClick = { which: Property ->
                                // TODO:: onClick
                                logger.d("onClick() -> which = $which")
                            })
                    }
                })
        })

}

@Preview(widthDp = 240, heightDp = 480)
@Composable
fun ListingsPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Listings(viewModel = ListingsViewModel(propertiesRepository = object :
                PropertiesRepository {
                override suspend fun getPropertiesByType(
                    type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
                ): List<Property> {
                    TODO("Not yet implemented")
                }

                override fun getPropertiesByTypeStream(
                    type: Property.Type, offset: Int, limit: Int, sort: Property.Sort
                ): Flow<List<Property>> {
                    TODO("Not yet implemented")
                }

                override fun getProperty(propertyId: String): Flow<Property> {
                    TODO("Not yet implemented")
                }

                override suspend fun searchProperties(
                    keyword: String, offset: Int, limit: Int, sort: Property.Sort
                ): List<Property> {
                    TODO("Not yet implemented")
                }

                override fun searchPropertiesStream(
                    keyword: String, offset: Int, limit: Int, sort: Property.Sort
                ): Flow<List<Property>> {
                    TODO("Not yet implemented")
                }
            }), logger = Logger.withTag("ListingsPreview"))
        }
    }
}

@Composable
fun ListingFeaturedPropertyItem(
    which: Property, onClick: ((Property) -> Unit), modifier: Modifier = Modifier
) {

    val itemWidth = 176.dp
    val bannerHeight = 132.dp

    ElevatedCard(
        modifier = modifier
            .width(itemWidth)
            .clickable { onClick(which) },
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Row(
                modifier = modifier
                    .height(bannerHeight)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Image(
                    imageVector = AppIcons.Location,    // TODO:: Use coil.compose.AsyncImage to load [Property.thumbnail]
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillHeight,
                )
            }

            /*
            * View Button w/ Proper Status as button label)
            * i.e. "For Sale", "For Rent", [Default]"View"
            */
            Row(
                modifier = modifier.padding(horizontal = 4.dp, vertical = 4.dp),
            ) {
                AppFilledButton(
                    onClick = {
                        // TODO::
                    },
                    modifier = modifier
                        .height(24.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .clipToBounds(),
                    small = true,
                    colors = AppButtonDefaults.filledButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = when (which.propStatus) {
                            Property.Status.ForSale -> "For Sale"
                            Property.Status.ForRent -> "For Rent"
                            else/*Property.Status.NotForSale*/ -> "View"
                        }.uppercase(LocalContext.current.resources.configuration.locales.get(0)),
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            /*
            * Property Price
            * i.e. "Price: $4,000,000.00"
            */
            ProvideTextStyle(value = MaterialTheme.typography.bodySmall) {
                Text(
                    modifier = modifier.padding(horizontal = 4.dp),
                    text = "Price: \$${DecimalFormat("###,###,###,###,###,##0.00").format(which.price ?: 0L)}",
                    fontWeight = FontWeight.SemiBold,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            /*
            * Property Name
            * i.e. "Okinawa Summer House"
            */
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                Text(
                    modifier = modifier.padding(horizontal = 4.dp),
                    text = which.address?.neighborhoodName ?: "N/A",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.sp,
                    lineHeight = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            /*
            * Property Attributes
            * i.e. "5 beds • 4 bathrooms • 4,402 sqft"
            */
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                Text(
                    modifier = modifier.padding(horizontal = 4.dp),
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.sp,
                    lineHeight = 10.sp,
                    text = mutableListOf<String>().apply {
                        add("${which.beds ?: 0} bed(s)")
                        add("${which.baths ?: 0} bathroom(s)")
                        val size = which.buildingSize?.size ?: 0
                        val units = which.buildingSize?.units ?: "sqft"
                        add("$size $units")
                    }.joinToString(separator = " • "),
                )
            }

            // Bottom Card Space
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

}

@Preview(widthDp = 156)
@Composable
fun ListingFeaturedPropertyItemPreview() {
    AppTheme {
        ListingFeaturedPropertyItem(
            which = Property(
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
            onClick = {},
        )
    }
}