package devlcc.io.kmmshowcaserealestate.android.app.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.component.AppFilledButton
import devlcc.io.kmmshowcaserealestate.android.core.designsystem.theme.AppTheme

@Composable
fun OnboardingScreen(
    modifier: Modifier,
    onNavigateToHome: (() -> Unit) = {},
    // ViewModel
) {
    Box(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "OnboardingScreen",
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                AppFilledButton(
                    // modifier = modifier.wrapContentSize(align = Alignment.Center),
                    onClick = { onNavigateToHome() }
                ) {
                    Text(
                        text = "Go to Home",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

    }

}


// internal fun OnboardingScreen(
//  // OnboardingUiState
//) {
//
//}

@Preview//(widthDp = 240, heightDp = 480)
@Composable
fun OnboardingScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
        ) {
            OnboardingScreen(modifier = Modifier)
        }
    }
}
