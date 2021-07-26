package co.ruizhang.cruddemo.ui.repodetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme
import org.koin.androidx.compose.get


@Composable
fun RepoDetail(
    id: Int,
    back: () -> Unit,
    vm: RepoDetailViewModel = get(),
    modifier: Modifier = Modifier, // leave it for now
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewDataFlowLifecycleAware = remember(vm.repo, lifecycleOwner) {
        vm.repo.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val repo = viewDataFlowLifecycleAware.collectAsState(null)

    vm.get(id)
    CrudDemoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.repo_detail)) },
                    navigationIcon = {
                        IconButton(onClick = back) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.content_description_back)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            repo.value?.let {
                Column(modifier = modifier) {
                    Text("Detail")
                    Text(text = it.name)
                    Text(text = it.url)
                }
            }
        }
    }
}
