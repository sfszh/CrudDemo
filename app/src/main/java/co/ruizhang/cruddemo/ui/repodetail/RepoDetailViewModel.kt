package co.ruizhang.cruddemo.ui.repodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.ui.STOP_TIME_OUT_MILLS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RepoDetailViewModel constructor(
    reposRepository: ReposRepository
) : ViewModel() {
    private var id = 0
    val repo: StateFlow<Repository?> = reposRepository.getRepos()
        .map { list ->
            list.first {
                it.id == id
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIME_OUT_MILLS),
            initialValue = null
        )


    fun get(id: Int) {
        this.id = id
    }
}