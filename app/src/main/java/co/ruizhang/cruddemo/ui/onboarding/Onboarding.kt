package co.ruizhang.cruddemo.ui.onboarding

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun Onboarding(
    vm: OnboardingViewModel = hiltViewModel(),
    onboardingComplete: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewDataFlowLifecycleAware = remember(vm.finishEvent, lifecycleOwner) {
        vm.finishEvent.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }

    val finished = viewDataFlowLifecycleAware.collectAsState(null)


    if (finished.value?.isFinished == true) {
        onboardingComplete()
    }
    var stripeState by remember { mutableStateOf(StripeState.Collapsed) }

    CrudDemoTheme {
        Scaffold(
            floatingActionButton = {
                ConfirmButton {
                    vm.markSplashViewed()
                }
            }
        ) {
            Box {
                CanvasStripeAnimation(
                    Modifier
                        .rotate(225f)
                        .fillMaxSize()
                        .offset(y = ((-50).dp)), //Fixme magic number and doesn't seem to fit every screen
                    state = stripeState
                )
                stripeState = StripeState.Expanded

                Column(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .wrapContentSize(Alignment.Center)
                ) {


                    Text(
                        text = stringResource(R.string.useless),
                        textAlign = TextAlign.Center,
                        fontSize = 64.sp,
                    )

                }

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

enum class StripeState {
    Collapsed,
    Expanded
}

data class StripeSpec(
    val color: Color,
    val height: Dp
)

val specsTemplate: List<StripeSpec> = listOf(
    StripeSpec(
        Color.Green,
        400.dp
    ),
    StripeSpec(
        Color.Yellow,
        500.dp
    ),
    StripeSpec(
        Color.Blue,
        600.dp
    ),
    StripeSpec(
        Color.Red,
        700.dp
    ),
    StripeSpec(
        Color.Blue,
        600.dp
    ),
    StripeSpec(
        Color.Yellow,
        500.dp
    ),
    StripeSpec(
        Color.Green,
        400.dp
    ),
)

const val STRIPE_STATE_NAME = "StripeState"


@Composable
private fun CanvasStripeAnimation(
    modifier: Modifier = Modifier,
    specs: List<StripeSpec> = specsTemplate,
    state: StripeState
) {

    val transition = updateTransition(targetState = state, label = STRIPE_STATE_NAME)

    val sizes = specs
        .map { spec ->
            val size by transition.animateSize(label = STRIPE_STATE_NAME) { stripeState ->
                when (stripeState) {
                    StripeState.Collapsed -> Size(15.dp.value, 0f)
                    StripeState.Expanded -> Size(15.dp.value, spec.height.value)
                }
            }
            return@map size
        }
        .map { size ->
            val animatedSize: Size by animateSizeAsState(
                targetValue = size,
                // Configure the animation duration and easing.
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            return@map animatedSize
        }

    Canvas(
        modifier = modifier
    ) {
        val canvasQuadrantSize = size / 20F

        specs.forEachIndexed { index, stripeSpec ->
            drawRect(
                color = stripeSpec.color,
                topLeft = Offset(index * canvasQuadrantSize.width, 0f),
                size = sizes[index]
            )
        }
    }
}


@Preview(name = "Stripe Animation Preview")
@Composable
private fun StripeAnimationPreview() {

    CrudDemoTheme {
        Scaffold {
            val stripeState by remember { mutableStateOf(StripeState.Expanded) }
            CanvasStripeAnimation(
                modifier = Modifier
                    .rotate(225f)
                    .fillMaxSize()
                    .offset(y = ((-0).dp)),
                state = stripeState
            )
        }
    }
}

