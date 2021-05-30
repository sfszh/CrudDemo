package co.ruizhang.cruddemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*


class ReposViewModel (private val reposRepository: ReposRepository): ViewModel() {
    val user: LiveData<List<Repository>> = liveData {
        val repos = reposRepository.getRepos()
        emit(repos)
    }
}