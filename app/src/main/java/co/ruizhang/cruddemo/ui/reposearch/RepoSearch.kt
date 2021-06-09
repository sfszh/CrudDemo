package co.ruizhang.cruddemo.ui.reposearch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.data.MOCK_REPOS
import co.ruizhang.cruddemo.data.MOCK_SEARCH
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme

@Composable
fun RepoSearch(
    modifier: Modifier = Modifier,
    vm: RepoSearchViewModel = hiltViewModel()
) {

    val searchResult = vm.searchResult.observeAsState()
    RepoSearchUI(
        search = { vm.search(it) },
        check = { viewData, isChecked ->
            vm.toggleBookmark(viewData, isChecked)
        },
        searchResult = searchResult,
        modifier = modifier
    )
}

@Composable
private fun RepoSearchUI(
    search: (String) -> Unit,
    check: (RepoSearchViewData, Boolean) -> Unit,
    searchResult: State<List<RepoSearchViewData>?>,
    modifier: Modifier = Modifier,
) {
    CrudDemoTheme {
        Scaffold(
            topBar = {
                SearchBar(onSearch = {
                    search(it)
                })
            }
        ) { innerPadding ->
            LazyColumn(modifier) {
                searchResult.value?.let {
                    items(it) { viewData ->
                        RepoSearchCard(
                            viewData = viewData,
                            onChecked = { isChecked ->
                                check(viewData, isChecked)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    Row(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = stringResource(id = R.string.search_label)) },
            leadingIcon = {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.search_content_description)
                )
            }
        )
        Button(
            onClick = {
                onSearch(text)
            },
            modifier = Modifier.align(CenterVertically)
        ) {
            Text(text = stringResource(id = R.string.search))
        }

    }
}

@Composable
fun RepoSearchCard(
    viewData: RepoSearchViewData,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier, // leave it for now
) {
    Row(modifier = modifier.width(IntrinsicSize.Max)) {
        Column {
            Text(
                modifier = Modifier.wrapContentWidth(Alignment.Start),
                text = viewData.name
            )
        }
        Checkbox(
            modifier = Modifier
                .align(CenterVertically)
                .wrapContentWidth(Alignment.End),
            checked = viewData.isChecked,
            onCheckedChange = onChecked
        )
    }
}

//region Previews
@Preview("Repo Search Preview")
@Composable
fun RepoSearchPreview() {
    RepoSearchUI(
        search = {},
        check = { _, _ ->
        },
        searchResult = remember { mutableStateOf(MOCK_SEARCH) })
}

@Preview("Search Bar Preview")
@Composable
fun SearchBarPreview() {
    SearchBar(onSearch = {})
}

@Preview("Search Card Preview")
@Composable
fun SearchCardPreview() {
    val viewData = RepoSearchViewData(1, "repo name", false)
    RepoSearchCard(viewData = viewData, onChecked = {})
}
//endregion