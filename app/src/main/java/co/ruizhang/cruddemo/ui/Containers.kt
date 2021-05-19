package co.ruizhang.cruddemo.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun PageContainer(finishActivity: () -> Unit) {
    ProvideWindowInsets {
        CrudDemoTheme {
            Scaffold {

            }
        }
    }
}