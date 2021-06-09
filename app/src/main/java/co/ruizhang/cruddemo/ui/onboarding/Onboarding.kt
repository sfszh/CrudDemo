package co.ruizhang.cruddemo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun Onboarding(onboardingComplete: () -> Unit) {
    CrudDemoTheme {
        Scaffold(
            floatingActionButton = {
                ConfirmButton(onboardingComplete)
            }
        ) { innerPadding ->
            Column (
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)
                    ){
                Text(text = stringResource(R.string.useless),
                textAlign = TextAlign.Center,
                fontSize = 64.sp)
            }
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