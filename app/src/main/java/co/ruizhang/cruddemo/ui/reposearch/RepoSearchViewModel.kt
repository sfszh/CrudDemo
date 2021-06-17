package co.ruizhang.cruddemo.ui.reposearch

import androidx.lifecycle.*
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.data.SearchRepository
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
    @ExperimentalCoroutinesApi
    private val searchEvent = MutableSharedFlow<String>(1)
    private var query : String = ""

    @ExperimentalCoroutinesApi
    @FlowPreview
    private val searchResult = searchEvent
        .flatMapConcat { query ->
            flow {
                if (query.isNotEmpty()) {
                    emit(searchRepository.search(query))
                }
            }
        }

    @ExperimentalCoroutinesApi
    @FlowPreview //really...
    val searchViewData: LiveData<List<RepoSearchViewData>> = searchResult
        .combine(reposRepository.getRepos()) { searchResult, repos ->
            toViewData(searchResult, repos)
        }
        .asLiveData()

    fun setQueryText( query : String) {
        this.query = query
    }

    @ExperimentalCoroutinesApi
    fun search() {
        viewModelScope.launch {
            searchEvent.emit(query)
        }
    }

    @ExperimentalCoroutinesApi
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
    ) = results.map { result ->
        val id = result.id
        val name = result.name
        val isChecked = repos.map { it.id }.contains(result.id)
        RepoSearchViewData(id, name, isChecked)
    }
}

data class RepoSearchViewData(
    val id: Int,
    val name: String,
    val isChecked: Boolean = false
)