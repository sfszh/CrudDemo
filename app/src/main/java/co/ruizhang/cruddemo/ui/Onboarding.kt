package co.ruizhang.cruddemo.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun Onboarding(onboardingComplete: () -> Unit) {
    CrudDemoTheme {
        Scaffold(
            floatingActionButton = {
                ConfirmButton(onboardingComplete)
            }
        ) {

        }
    }
}

@Composable
fun ConfirmButton(onboardingComplete: () -> Unit) {
    FloatingActionButton(
        onClick = onboardingComplete,
    ) {
        Text(text = stringResource(R.string.end_onboarding))
    }
}

@Preview(name = "Onboarding")
@Composable
private fun OnboardingPreview() {
    Onboarding(onboardingComplete = { })
}