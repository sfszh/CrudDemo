package co.ruizhang.cruddemo.ui.repodetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    reposRepository: ReposRepository
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