package co.ruizhang.cruddemo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.ruizhang.cruddemo.data.Mock_Repos
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun Repos(
    repos: List<Repository>,
    selectRepo: (Int) -> Unit,
    modifier : Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(repos) { repo ->
            RepoCard(
                repo = repo,
                onClick = { selectRepo(repo.id) },
                modifier = modifier
            )
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
    CrudDemoTheme {
        Repos(repos = Mock_Repos, selectRepo = {})
    }
}


@Preview("Repo Card Preview")
@Composable
fun RepoCardPreview() {
    val mockData = Mock_Repos[0]

    CrudDemoTheme {
        RepoCard(repo = mockData, onClick = {})
    }
}