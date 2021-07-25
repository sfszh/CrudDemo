package co.ruizhang.cruddemo.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.DataStoreManager
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.ui.STOP_TIME_OUT_MILLS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    reposRepository: ReposRepository,
    dataStoreManager: DataStoreManager
) : ViewModel() {
    val repos: StateFlow<List<Repository>> = reposRepository.getRepos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIME_OUT_MILLS),
            initialValue = emptyList()
        )


    val hasSplashViewed: Flow<Boolean> = dataStoreManager.hasSplashViewed

    val uiState: StateFlow<RepoListUIState> =
        dataStoreManager.hasSplashViewed
            .combine(reposRepository.getRepos()) { hasSplashViewed, repos ->
                RepoListUIState(hasSplashViewed, repos)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(STOP_TIME_OUT_MILLS),
                initialValue = RepoListUIState(null, emptyList())
            )
}

data class RepoListUIState(
    val hasSplashViewed: Boolean?,
    val repos: List<Repository>
)