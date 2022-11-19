package devlcc.io.kmmshowcaserealestate.android.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon.AppIcons
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon.Icon

/**
 * App Property Attribute chip with included leading attrib icon as well as text content slot.
 *
 * @param onClick Called when the user clicks the chip.
 * @param modifier Modifier to be applied to the chip.
 * @param enabled Controls the enabled state of the chip. When `false`, this chip will not be
 * clickable and will appear disabled to accessibility services.
 * @param icon The property attribute icon.
 * @param text The text label content describing the attribute.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppPropertyAttribChip(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    icon: Icon,
    text: String,
) {
    AssistChip(
        onClick = { onClick?.invoke() },
        label = {
            ProvideTextStyle(value = MaterialTheme.typography.labelMedium) {
                Text(
                    text = text,
                )
            }
        },
        modifier = modifier,
        enabled = enabled,
        leadingIcon = {
            when (icon) {
                is Icon.ImageVectorIcon -> Image(
                    imageVector = icon.imageVector,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                )
                is Icon.DrawableResourceIcon -> Image(
                    painter = painterResource(id = icon.resId),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        border = AssistChipDefaults.assistChipBorder(
            borderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent.copy(
                alpha = AppChipDefaults.DisabledChipContentAlpha
            ),
            borderWidth = 0.dp,
        ),
        colors = AssistChipDefaults.assistChipColors(
        containerColor = MaterialTheme.colorScheme.background,/*md_theme_light_background*/
        labelColor = MaterialTheme.colorScheme.primary,/*md_theme_light_primary*/
        leadingIconContentColor = MaterialTheme.colorScheme.primary,/*md_theme_light_primary*/
        disabledContainerColor = MaterialTheme.colorScheme.background.copy(
            alpha = AppChipDefaults.DisabledChipContainerAlpha
        ),
        disabledLabelColor = MaterialTheme.colorScheme.primary.copy(
            alpha = AppChipDefaults.DisabledChipContentAlpha
        ),
        disabledLeadingIconContentColor = MaterialTheme.colorScheme.primary.copy(
            alpha = AppChipDefaults.DisabledChipContentAlpha
        ),
    )
    )
}

@Preview(group = "AppTextButton")
@Composable
fun AppPropertyAttribChipPreview() {
    AppPropertyAttribChip(
        icon = Icon.ImageVectorIcon(imageVector = AppIcons.FloorArea),
        text = "102 sqm",
    )
}

/**
 * App chip default values.
 */
object AppChipDefaults {
    const val DisabledChipContainerAlpha = 0.12f
    const val DisabledChipContentAlpha = 0.38f
}