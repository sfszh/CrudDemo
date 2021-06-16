package co.ruizhang.cruddemo.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) : ViewModel(){
    @ExperimentalCoroutinesApi
    private val _finishEvent = MutableSharedFlow<Unit>(0)
    @ExperimentalCoroutinesApi
    val finishEvent = _finishEvent.asLiveData()

    @ExperimentalCoroutinesApi
    fun markSplashViewed() {
        viewModelScope.launch {
            dataStoreManager.recordSplashViewed()
            _finishEvent.emit(Unit)
        }
    }
}