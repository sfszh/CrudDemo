package co.ruizhang.cruddemo.ui.reposearch

import androidx.lifecycle.*
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.data.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RepoSearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val reposRepository: ReposRepository
) : ViewModel() {

    val searchResult: MutableLiveData<List<RepoSearchViewData>> by lazy {
        MutableLiveData<List<RepoSearchViewData>>()
    }
    private var searchCache: List<Repository> = emptyList()

    fun search(query: String) {
        viewModelScope.launch {
            try {


                searchCache = searchRepository.search(query)
                val repos = reposRepository.getRepos()
                val viewData = toViewData(searchCache, repos)
                searchResult.setValue(viewData)
            } catch ( e : Exception) {
                print(e.message)
            }
        }
    }

    fun toggleBookmark(viewData: RepoSearchViewData, isChecked: Boolean) {
        viewModelScope.launch {
            searchCache.first { it.name == viewData.name }
            val toggledRepo = searchCache.first { it.id == viewData.id }
            val savedRepos =
                if (isChecked) reposRepository.addRepos(toggledRepo) else reposRepository.removeRepos(
                    toggledRepo
                )
            val newViewData = toViewData(searchCache, savedRepos)
            searchResult.setValue(newViewData)
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