package co.ruizhang.cruddemo.ui.repodetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val reposRepository: ReposRepository
) : ViewModel() {

    val repo: MutableLiveData<Repository> by lazy {
        MutableLiveData<Repository>()
    }

    fun get(id: Int) {
        viewModelScope.launch {
            val detail = reposRepository.getRepos().firstOrNull { it.id == id }
            detail?.let { repo.setValue(it) }
        }
    }
}