package co.ruizhang.cruddemo.ui.reposearch

import androidx.lifecycle.*
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.data.SearchRepository
import co.ruizhang.cruddemo.ui.STOP_TIME_OUT_MILLS
import co.ruizhang.cruddemo.ui.repos.RepoListViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoSearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val reposRepository: ReposRepository
) : ViewModel() {

    private val searchEvent = MutableSharedFlow<String>(1)
    private var query: String = ""


    @FlowPreview
    private val searchResult = searchEvent
        .flatMapConcat { query -> //todo find a more proper operator
            flow {
                if (query.isNotEmpty()) {
                    emit(searchRepository.search(query))
                }
            }
        }

    @FlowPreview //really...
    val viewData: StateFlow<List<RepoSearchViewData>> = searchResult
        .combine(reposRepository.getRepos()) { searchResult, repos ->
            toViewData(searchResult, repos)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIME_OUT_MILLS),
            initialValue = emptyList()
        )

    fun setQueryText(query: String) {
        this.query = query
    }


    fun search() {
        viewModelScope.launch {
            searchEvent.emit(query)
        }
    }

    @FlowPreview
    fun toggleBookmark(viewData: RepoSearchViewData, isChecked: Boolean) {
        viewModelScope.launch {
            searchResult
                .onEach { repoList ->
                    val toggledRepo = repoList.first { it.id == viewData.id }
                    if (isChecked) reposRepository.addRepos(toggledRepo) else reposRepository.removeRepos(
                        toggledRepo
                    )
                }
                .collect()
        }
    }

    private fun toViewData(
        results: List<Repository>,
        repos: List<Repository>
    ): List<RepoSearchViewData> {
        return results.map { result ->
            val id = result.id
            val name = result.name
            val isChecked = repos.map { it.id }.contains(result.id)
            RepoSearchViewData(id, name, isChecked)
        }
    }
}

data class RepoSearchViewData(
    val id: Int,
    val name: String,
    val isChecked: Boolean = false
)