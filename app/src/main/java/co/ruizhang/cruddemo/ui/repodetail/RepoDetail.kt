package co.ruizhang.cruddemo.ui.repodetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.data.MOCK_REPOS
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun RepoDetail(
    id: Int,
    vm: RepoDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier, // leave it for now
) {
    val navController = rememberNavController()
    val repo = vm.repo.observeAsState()
    vm.get(id)
    CrudDemoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.repo_detail)) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
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
