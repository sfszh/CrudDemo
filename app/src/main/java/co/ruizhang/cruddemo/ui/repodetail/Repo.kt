package co.ruizhang.cruddemo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.ruizhang.cruddemo.data.MOCK_REPOS
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun RepoDetail(
    repoId: Int,
    modifier: Modifier = Modifier, // leave it for now
) {
    val repo = MOCK_REPOS.first { it.id == repoId }
    CrudDemoTheme {
        Column(modifier = modifier) {
            Text("Detail")
            Text(text = repo.name)
            Text(text = repo.url)
        }
    }
}


@Preview("Repo Detail Preview")
@Composable
fun RepoDetailPreview() {
    RepoDetail(0)
}
