package co.ruizhang.cruddemo.ui.reposearch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.ruizhang.cruddemo.R
import co.ruizhang.cruddemo.data.MOCK_SEARCH
import co.ruizhang.cruddemo.ui.theme.CrudDemoTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalComposeUiApi
@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun RepoSearch(
    modifier: Modifier = Modifier,
    vm: RepoSearchViewModel = hiltViewModel(),
    back: () -> Unit,
) {

    val searchResult = vm.searchViewData.observeAsState()
    RepoSearchUI(
        search = { vm.search() },
        onQueryChanged = { vm.setQueryText(it) },
        check = { viewData, isChecked ->
            vm.toggleBookmark(viewData, isChecked)
        },
        searchResult = searchResult,
        back = back,
        modifier = modifier
    )
}

@ExperimentalComposeUiApi
@Composable
private fun RepoSearchUI(
    search: () -> Unit,
    onQueryChanged: (String) -> Unit,
    check: (RepoSearchViewData, Boolean) -> Unit,
    searchResult: State<List<RepoSearchViewData>?>,
    back: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CrudDemoTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        SearchBar(
                            onSearch = search,
                            onTextChanged = onQueryChanged
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = back) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.content_description_back)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = search) {
                            Icon(
                                Icons.Rounded.Search,
                                contentDescription = stringResource(id = R.string.content_description_search)
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                contentPadding = innerPadding,
                modifier = modifier.padding(top = 8.dp)
            ) {
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

@ExperimentalComposeUiApi
@Composable
fun SearchBar(
    onSearch: () -> Unit,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Row(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onTextChanged(it)
            },
            leadingIcon = {
                Text(
                    text = stringResource(id = R.string.search),
                    color = MaterialTheme.colors.onPrimary
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
                keyboardController?.hide()
            })
        )
        Button(
            onClick = {
                onSearch()
                keyboardController?.hide()
            },
            modifier = Modifier
                .align(CenterVertically)
                .width(64.dp)
        ) {
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.button
            )
        }
    }

}


@Composable
fun RepoSearchCard(
    viewData: RepoSearchViewData,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier, // leave it for now
) {
    Row(
        modifier = modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
    ) {
        Column {
            Text(
                modifier = Modifier,
                text = viewData.name,
                style = MaterialTheme.typography.subtitle2
            )
        }
        Checkbox(
            modifier = Modifier
                .weight(1.0f)
                .wrapContentWidth(Alignment.End),
            checked = viewData.isChecked,
            onCheckedChange = onChecked
        )
    }
}

//region Previews
@ExperimentalComposeUiApi
@Preview("Repo Search Preview")
@Composable
fun RepoSearchPreview() {
    RepoSearchUI(
        search = {},
        onQueryChanged = {},
        check = { _, _ ->
        },
        searchResult = remember { mutableStateOf(MOCK_SEARCH) },
        back = {}
    )
}

@ExperimentalComposeUiApi
@Preview("Search Bar Preview")
@Composable
fun SearchBarPreview() {
    SearchBar(onSearch = {}, onTextChanged = {})
}

@Preview("Search Card Preview")
@Composable
fun SearchCardPreview() {
    val viewData = RepoSearchViewData(1, "repo name", false)
    RepoSearchCard(viewData = viewData, onChecked = {})
}
//endregion