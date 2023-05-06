package devlcc.io.kmmshowcaserealestate.android.app.home.listings

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.component.AppButtonDefaults
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.component.AppFilledButton
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon.AppIcons
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme
import devlcc.io.kmmshowcaserealestate.core.model.property.Address
import devlcc.io.kmmshowcaserealestate.core.model.property.BuildingSize
import devlcc.io.kmmshowcaserealestate.core.model.property.Property
import java.text.DecimalFormat

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
                            else/*Property.Status.NotForSale*/ -> "Not for Sale"
                        }.uppercase(
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                LocalContext.current.resources.configuration.locales.get(0)
                            } else {
                                @Suppress("DEPRECATION")
                                LocalContext.current.resources.configuration.locale
                            }
                        ),
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