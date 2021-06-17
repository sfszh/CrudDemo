package co.ruizhang.cruddemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.data.MOCK_REPOS
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.ui.repos.RepoListViewModel
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun Repos(
    modifier: Modifier = Modifier,
    vm: RepoListViewModel = hiltViewModel(),
    selectRepo: (Int) -> Unit,
    navigateToRepoSearch: () -> Unit,
    navigateToSplash: () -> Unit
) {
    vm.hasSplashViewed.observeAsState().value?.let { hasViewed ->
        if (hasViewed) {
            val repos = vm.repos.observeAsState()
            ReposUI(modifier, repos, selectRepo, navigateToRepoSearch)
        } else {
            navigateToSplash()
        }
    }
}

@Composable
private fun ReposUI(
    modifier: Modifier = Modifier,
    repos: State<List<Repository>?>,
    selectRepo: (Int) -> Unit,
    navigateToRepoSearch: () -> Unit
) {
    CrudDemoTheme {
        Scaffold(
            floatingActionButton = {
                AddRepoButton {
                    navigateToRepoSearch()
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.repo_list)) }
                )
            }


        ) { _ ->
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                repos.value?.let {
                    items(it) { repo ->
                        RepoCard(
                            repo = repo,
                            onClick = { selectRepo(repo.id) },
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RepoCard(
    repo: Repository,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // leave it for now
) {
    Card(
        elevation = 2.dp,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick)
        ) {
            Text(text = repo.name, style = MaterialTheme.typography.subtitle2)
            Text(text = repo.url, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun AddRepoButton(add: () -> Unit) {
    FloatingActionButton(
        onClick = add,
    ) {
        Text(text = stringResource(R.string.add_repo))
    }
}


@Preview("Repos Preview")
@Composable
fun ReposPreview() {
    ReposUI(
        repos = remember { mutableStateOf(MOCK_REPOS) },
        selectRepo = {},
        navigateToRepoSearch = {})
}


@Preview("Repo Card Preview")
@Composable
fun RepoCardPreview() {
    val mockData = MOCK_REPOS[0]

    CrudDemoTheme {
        RepoCard(repo = mockData, onClick = {})
    }
}