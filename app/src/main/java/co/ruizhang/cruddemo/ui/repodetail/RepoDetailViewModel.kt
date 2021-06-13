package co.ruizhang.cruddemo.ui.repodetail

import androidx.lifecycle.*
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val reposRepository: ReposRepository
) : ViewModel() {
    private var id = 0
    val repo: LiveData<Repository> = reposRepository.getRepos()
        .map { list ->
            list.first {
                it.id == id
            }
        }
        .asLiveData()

    fun get(id: Int) {
        this.id = id
    }
}