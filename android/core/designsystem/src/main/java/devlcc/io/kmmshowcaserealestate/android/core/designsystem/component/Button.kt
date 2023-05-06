package devlcc.io.kmmshowcaserealestate.android.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon.AppIcons
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.icon.Icon
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.*

/**
 * App filled button with generic content slot. Wraps Material 3 [Button].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.filledButtonColors].
 * @param contentPadding The spacing values to apply internally between the container and the
 * content. See [AppButtonDefaults.buttonContentPadding].
 * @param content The button content.
 */
@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.filledButtonColors(),
    contentPadding: PaddingValues = AppButtonDefaults.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefaults.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                content()
            }
        }
    )
}

/**
 * App filled button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.filledButtonColors].
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content. Pass `null` here for no trailing icon.
 */
@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.filledButtonColors(),
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    AppFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        colors = colors,
        contentPadding = AppButtonDefaults.buttonContentPadding(
            small = small,
            leadingIcon = leadingIcon != null,
            trailingIcon = trailingIcon != null
        )
    ) {
        AppButtonContent(
            text = {
                ProvideTextStyle(
                    value = MaterialTheme.typography.labelSmall.copy(
                        color = when(enabled) {
                            true -> MaterialTheme.typography.labelSmall.color
                            false -> MaterialTheme.colorScheme.outline
                        }
                    )
                ) {
                    text()
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Preview(group = "AppFilledButton", widthDp = 120)
@Composable
fun AppFilledButtonPreview() {
    AppFilledButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = md_theme_light_primary,
            contentColor = Color.White
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}

@Preview(group = "AppFilledButton", widthDp = 120)
@Composable
fun AppFilledButtonPreviewDisabled() {
    AppFilledButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        enabled = false,
        colors = AppButtonDefaults.filledButtonColors(
            containerColor = md_theme_light_primary,
            contentColor = Color.White
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}

/**
 * App outlined button with generic content slot. Wraps Material 3 [OutlinedButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param border Border to draw around the button. Pass `null` here for no border.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.outlinedButtonColors].
 * @param contentPadding The spacing values to apply internally between the container and the
 * content. See [AppButtonDefaults.buttonContentPadding].
 * @param content The button content.
 */
@Composable
fun AppOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    border: BorderStroke? = AppButtonDefaults.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = AppButtonDefaults.outlinedButtonColors(),
    contentPadding: PaddingValues = AppButtonDefaults.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefaults.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                content()
            }
        }
    )
}

/**
 * App outlined button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param border Border to draw around the button. Pass `null` here for no border.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.outlinedButtonColors].
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content. Pass `null` here for no trailing icon.
 */
@Composable
fun AppOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    border: BorderStroke? = AppButtonDefaults.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = AppButtonDefaults.outlinedButtonColors(),
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    AppOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        border = border,
        colors = colors,
        contentPadding = AppButtonDefaults.buttonContentPadding(
            small = small,
            leadingIcon = leadingIcon != null,
            trailingIcon = trailingIcon != null
        )
    ) {
        AppButtonContent(
            text = {
                ProvideTextStyle(
                    value = MaterialTheme.typography.labelSmall.copy(
                        color = when(enabled) {
                            true -> MaterialTheme.typography.labelSmall.color
                            false -> MaterialTheme.colorScheme.outline
                        }
                    )
                ) {
                    text()
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Preview(group = "AppOutlinedButton", widthDp = 120)
@Composable
fun AppOutlinedButtonPreviewSmall() {
    AppOutlinedButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = true,
        border = AppButtonDefaults.outlinedButtonBorder(
            enabled = true,
            width = 2.dp,
            color = Color.DarkGray,
        ),
        colors = AppButtonDefaults.outlinedButtonColors(
            containerColor = md_theme_light_primary,
            contentColor = Color.White,
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}

@Preview(group = "AppOutlinedButton", widthDp = 120)
@Composable
fun AppOutlinedButtonPreviewNormal() {
    AppOutlinedButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = false,
        border = AppButtonDefaults.outlinedButtonBorder(
            enabled = true,
            width = 2.dp,
            color = Color.DarkGray,
        ),
        colors = AppButtonDefaults.outlinedButtonColors(
            containerColor = md_theme_light_primary,
            contentColor = Color.White,
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}

@Preview(group = "AppOutlinedButton", widthDp = 120)
@Composable
fun AppOutlinedButtonPreviewDisabled() {
    AppOutlinedButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = false,
        enabled = false,
        border = AppButtonDefaults.outlinedButtonBorder(
            enabled = false,
            width = 2.dp,
            color = Color.DarkGray,
        ),
        colors = AppButtonDefaults.outlinedButtonColors(
            containerColor = md_theme_light_primary,
            contentColor = Color.White,
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}


/**
 * App text button with generic content slot. Wraps Material 3 [TextButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.textButtonColors].
 * @param contentPadding The spacing values to apply internally between the container and the
 * content. See [AppButtonDefaults.buttonContentPadding].
 * @param content The button content.
 */
@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.textButtonColors(),
    contentPadding: PaddingValues = AppButtonDefaults.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefaults.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.labelSmall) {
                content()
            }
        }
    )
}

/**
 * App text button with text and icon content slots.
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.textButtonColors].
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content. Pass `null` here for no trailing icon.
 */
@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefaults.textButtonColors(),
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    AppTextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        colors = colors,
        contentPadding = AppButtonDefaults.buttonContentPadding(
            small = small,
            leadingIcon = leadingIcon != null,
            trailingIcon = trailingIcon != null
        )
    ) {
        AppButtonContent(
            text = {
                ProvideTextStyle(
                    value = MaterialTheme.typography.labelSmall.copy(
                        color = when(enabled) {
                            true -> MaterialTheme.typography.labelSmall.color
                            false -> MaterialTheme.colorScheme.outline
                        }
                    )
                ) {
                    text()
                }
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }
}

@Preview(group = "AppTextButton", widthDp = 120)
@Composable
fun AppTextButtonPreview() {
    AppTextButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = true,
        colors = AppButtonDefaults.textButtonColors(
            contentColor = Color.White
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}

@Preview(group = "AppTextButton", widthDp = 120)
@Composable
fun AppTextButtonPreviewDisabled() {
    AppTextButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        small = false,
        enabled = false,
        colors = AppButtonDefaults.textButtonColors(
            contentColor = Color.White
        ),
        text = {
            Text(text = "Get Started")
        },
    )
}

/**
 * App text button with generic content slot. Wraps Material 3 [TextButton].
 *
 * @param onClick Will be called when the user clicks the button.
 * @param modifier Modifier to be applied to the button.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not be
 * clickable and will appear disabled to accessibility services.
 * @param small Whether or not the size of the button should be small or regular.
 * @param colors [ButtonColors] that will be used to resolve the container and content color for
 * this button in different states. See [AppButtonDefaults.textButtonColors].
 * @param contentPadding The spacing values to apply internally between the container and the
 * content. See [AppButtonDefaults.buttonContentPadding].
 * @param content The button content.
 */
@Composable
fun AppImageButton(
    icon: Icon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: IconButtonColors = AppButtonDefaults.iconButtonColors(),
    contentDescription: String? = null,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.then(
            Modifier.clip(
                CircleShape
            )
                .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
        ),
        enabled = enabled,
        colors = colors,
        content = {
            when (icon) {
                is Icon.ImageVectorIcon -> Image(
                    imageVector = icon.imageVector,
                    contentDescription = contentDescription,
                    colorFilter = ColorFilter.tint(
                        when(enabled) {
                            true -> MaterialTheme.colorScheme.onPrimary
                            false -> MaterialTheme.colorScheme.outline
                        }
                    ),
                )
                is Icon.DrawableResourceIcon -> Image(
                    painter = painterResource(id = icon.resId),
                    contentDescription = contentDescription,
                    colorFilter = ColorFilter.tint(
                        when(enabled) {
                            true -> MaterialTheme.colorScheme.onPrimary
                            false -> MaterialTheme.colorScheme.outline
                        }
                    ),
                )
            }
        }
    )
}

@Preview(group = "AppImageButton", widthDp = 48, heightDp = 48)
@Composable
fun AppImageButtonPreview() {
    AppImageButton(
        icon = Icon.ImageVectorIcon(imageVector = AppIcons.Call),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        contentDescription = "Call",
    )
}

@Preview(group = "AppImageButton", widthDp = 48, heightDp = 48)
@Composable
fun AppImageButtonPreviewDisabled() {
    AppImageButton(
        icon = Icon.ImageVectorIcon(imageVector = AppIcons.Message),
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        enabled = false,
        contentDescription = "Message",
    )
}

/**
 * Internal App button content layout for arranging the text label, leading icon and
 * trailing icon.
 *
 * @param text The button text label content.
 * @param leadingIcon The button leading icon content. Pass `null` here for no leading icon.
 * @param trailingIcon The button trailing icon content. Pass `null` here for no trailing icon.
 */
@Composable
private fun RowScope.AppButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = AppButtonDefaults.ButtonIconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) {
                    AppButtonDefaults.ButtonContentSpacing
                } else {
                    0.dp
                },
                end = if (trailingIcon != null) {
                    AppButtonDefaults.ButtonContentSpacing
                } else {
                    0.dp
                }
            )
    ) {
        text()
    }
    if (trailingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = AppButtonDefaults.ButtonIconSize)) {
            trailingIcon()
        }
    }
}

/**
 * App button default values.
 */
object AppButtonDefaults {
    val SmallButtonHeight = 32.dp
    const val DisabledButtonContainerAlpha = 0.12f
    const val DisabledButtonContentAlpha = 0.38f
    val ButtonHorizontalPadding = 24.dp
    val ButtonHorizontalIconPadding = 16.dp
    val ButtonVerticalPadding = 8.dp
    val SmallButtonHorizontalPadding = 16.dp
    val SmallButtonHorizontalIconPadding = 12.dp
    val SmallButtonVerticalPadding = 7.dp
    val ButtonContentSpacing = 8.dp
    val ButtonIconSize = 18.dp
    fun buttonContentPadding(
        small: Boolean,
        leadingIcon: Boolean = false,
        trailingIcon: Boolean = false
    ): PaddingValues {
        return PaddingValues(
            start = when {
                small && leadingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                leadingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            top = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding,
            end = when {
                small && trailingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                trailingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            bottom = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding
        )
    }
    @Composable
    fun filledButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primary,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = MaterialTheme.colorScheme.primary.copy(
            alpha = DisabledButtonContainerAlpha
        ),
        disabledContentColor: Color = MaterialTheme.colorScheme.onPrimary.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.buttonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
    @Composable
    fun outlinedButtonBorder(
        enabled: Boolean,
        width: Dp = 1.dp,
        color: Color = MaterialTheme.colorScheme.onPrimary,
        disabledColor: Color = MaterialTheme.colorScheme.onPrimary.copy(
            alpha = DisabledButtonContainerAlpha
        )
    ): BorderStroke = BorderStroke(
        width = width,
        color = if (enabled) color else disabledColor
    )
    @Composable
    fun outlinedButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = MaterialTheme.colorScheme.onPrimary.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.outlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Transparent,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = Color.Transparent,
        disabledContentColor: Color = MaterialTheme.colorScheme.onPrimary.copy(
            alpha = DisabledButtonContentAlpha
        )
    ) = ButtonDefaults.textButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun iconButtonColors(
        containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
        contentColor: Color = MaterialTheme.colorScheme.onPrimary,
        disabledContainerColor: Color = MaterialTheme.colorScheme.onTertiaryContainer.copy(
            alpha = DisabledButtonContainerAlpha
        ),
        disabledContentColor: Color = MaterialTheme.colorScheme.onTertiary.copy(alpha = DisabledButtonContentAlpha)
    ) = IconButtonDefaults.iconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
}