package devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.R

/**
 * App icons. Material icons are [ImageVector]s, custom icons are drawable resource IDs.
 */
object AppIcons {

    val Bathroom = Icons.Outlined.Bathtub
    val Bedroom = Icons.Outlined.Bed
    val Call = Icons.Outlined.Call
    val Favorite = Icons.Outlined.Favorite
    val FloorArea = Icons.Outlined.Architecture
    val Location = Icons.Rounded.LocationCity
    val Message = Icons.Outlined.Sms
    val Share = Icons.Outlined.Share

    /*val Bookmark = R.drawable.ic_bookmark*/
    /*val BookmarkBorder = R.drawable.ic_bookmark_border*/
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector): Icon()
    data class DrawableResourceIcon(@DrawableRes val resId: Int): Icon()
}