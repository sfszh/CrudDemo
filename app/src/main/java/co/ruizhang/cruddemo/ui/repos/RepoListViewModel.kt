package co.ruizhang.cruddemo.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import co.ruizhang.cruddemo.DataStoreManager
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(reposRepository: ReposRepository, dataStoreManager: DataStoreManager): ViewModel() {
    val repos: LiveData<List<Repository>> = reposRepository.getRepos().asLiveData()

    val hasSplashViewed : LiveData<Boolean> = dataStoreManager.hasSplashViewed.asLiveData()
}