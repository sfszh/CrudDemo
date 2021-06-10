package co.ruizhang.cruddemo.ui.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(private val reposRepository: ReposRepository): ViewModel() {
    val repos: LiveData<List<Repository>> = liveData {
        val repos = reposRepository.getRepos()
        emit(repos)
    }
}