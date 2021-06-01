package co.ruizhang.cruddemo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class ReposViewModel @Inject constructor(private val reposRepository: ReposRepository): ViewModel() {
//    val user: LiveData<List<Repository>> = liveData {
//        val repos = reposRepository.getRepos()
//        emit(repos)
//    }
//}

@HiltViewModel
class ReposViewModel @Inject constructor(): ViewModel() {
    val user: LiveData<List<Repository>> = liveData {
        emit(Mock_Repos)
    }
}