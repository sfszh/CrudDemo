package co.ruizhang.cruddemo.ui.repodetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.data.RepoDetailRepository
import co.ruizhang.cruddemo.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val repoDetailRepository: RepoDetailRepository
) : ViewModel() {

    val repo: MutableLiveData<Repository> by lazy {
        MutableLiveData<Repository>()
    }

    fun get(fullName: String) {
        viewModelScope.launch {
            val detail = repoDetailRepository.get(fullName)
            detail?.let { repo.setValue(it) }
        }
    }
}