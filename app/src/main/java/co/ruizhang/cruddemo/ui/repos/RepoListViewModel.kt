package co.ruizhang.cruddemo.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.DataStoreManager
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.ui.STOP_TIME_OUT_MILLS
import kotlinx.coroutines.flow.*

class RepoListViewModel constructor(
    reposRepository: ReposRepository,
    dataStoreManager: DataStoreManager
) : ViewModel() {
    val viewData: StateFlow<RepoListViewData> =
        dataStoreManager.hasSplashViewed
            .combine(reposRepository.getRepos()) { hasSplashViewed, repos ->
                RepoListViewData(hasSplashViewed, repos)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIME_OUT_MILLS),
                initialValue = RepoListViewData(null, emptyList())
            )
}

data class RepoListViewData(
    val hasSplashViewed: Boolean?,
    val repos: List<Repository>
)