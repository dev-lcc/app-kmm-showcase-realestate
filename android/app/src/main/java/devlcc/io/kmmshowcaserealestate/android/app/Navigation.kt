package devlcc.io.kmmshowcaserealestate.android.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import devlcc.io.kmmshowcaserealestate.android.app.home.favourites.FavouritesScreen
import devlcc.io.kmmshowcaserealestate.android.app.home.listings.ListingsScreen
import devlcc.io.kmmshowcaserealestate.android.app.home.listings.viewall.ViewAllListingsScreen
import devlcc.io.kmmshowcaserealestate.android.app.home.search.SearchScreen
import devlcc.io.kmmshowcaserealestate.android.app.onboarding.OnboardingScreen
import devlcc.io.kmmshowcaserealestate.android.app.propertydetails.PropertyDetailScreen
import devlcc.io.kmmshowcaserealestate.core.model.property.Property

@Composable
fun Navigation(
    modifier: Modifier,
    navHostController: NavHostController = rememberNavController(),
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Destination.Onboarding.path
    ) {

        // Onboarding
        composable(route = Destination.Onboarding.path) {
            OnboardingScreen(
                modifier = modifier,
                onNavigateToHome = {
                    navHostController
                        .navigate(Destination.Home.path) {
                            popUpTo(Destination.Onboarding.path) { inclusive = true }
                        }
                }
            )
        }

        // Home
        navigation(
            startDestination = Destination.Listings.path,
            route = Destination.Home.path,
        ) {
            composable(route = Destination.Listings.path) {
                ListingsScreen(
                    modifier = modifier,
                    onNavigateToDetail = { property: Property ->
                        val propertyId = property.propertyID ?: return@ListingsScreen
                        navHostController.navigate(
                            Destination.PropertyDetail.path.replace("{id}", propertyId)
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(route = Destination.Search.path) {
                SearchScreen(modifier = modifier)
            }

            composable(route = Destination.Favourites.path) {
                FavouritesScreen(modifier = modifier)
            }
        }

        // Property Detail
        composable(
            route = Destination.PropertyDetail.path,
            arguments = listOf(
                navArgument("id") {
                    this.type = NavType.StringType
                    this.nullable = false
                }
            )
        ) { backstackEntry ->
            val propertyId = backstackEntry.arguments?.getString("id") ?: return@composable
            PropertyDetailScreen(
                modifier = modifier,
                propertyId = propertyId,
            )
        }

        // View All(Properties by Status)
        composable(route = Destination.ViewAllListings.path) {
            ViewAllListingsScreen(modifier = modifier)
        }

    }

}

sealed class Destination(
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = false
) {

    companion object {
        fun fromString(route: String): Destination =
            when(route) {
                Onboarding.path -> Onboarding
                Home.path -> Home
                Listings.path -> Listings
                ViewAllListings.path -> ViewAllListings
                Search.path -> Search
                Favourites.path -> Favourites
                PropertyDetail.path -> PropertyDetail
                else -> Home
            }
    }

    object Onboarding: Destination("onboarding")

    object Home: Destination("home")

    object Listings: Destination("listings", null/*Listings BottomNav Icon*/, true)

    object ViewAllListings: Destination("viewalllistings/{status}")

    object Search: Destination("search", null/* Search BottomNav Icon */)

    object Favourites: Destination("favourites", null/*Favourites BottomNav Icon*/)

    object PropertyDetail: Destination("propertydetail/{id}")

}
