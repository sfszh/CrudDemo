package co.ruizhang.cruddemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import co.ruizhang.cruddemo.data.Mock_Repos
import co.ruizhang.cruddemo.ui.repos.ReposViewModel
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun Repos(
    vm: ReposViewModel,
    selectRepo: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val repos = vm.user.observeAsState()
    ReposUI(modifier, repos, selectRepo)
}

@Composable
private fun ReposUI(
    modifier: Modifier = Modifier,
    repos: State<List<Repository>?>,
    selectRepo: (Int) -> Unit
) {
    CrudDemoTheme {
        LazyColumn(modifier) {
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


@Composable
fun RepoCard(
    repo: Repository,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // leave it for now
) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Text(text = repo.name)
        Text(text = repo.url)
    }
}

@Preview("Repos Preview")
@Composable
fun ReposPreview() {
    ReposUI(repos = remember{ mutableStateOf(Mock_Repos) }, selectRepo = {})
}


@Preview("Repo Card Preview")
@Composable
fun RepoCardPreview() {
    val mockData = Mock_Repos[0]

    CrudDemoTheme {
        RepoCard(repo = mockData, onClick = {})
    }
}