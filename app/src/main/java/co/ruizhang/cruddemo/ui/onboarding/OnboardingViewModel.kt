package co.ruizhang.cruddemo.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.DataStoreManager
import co.ruizhang.cruddemo.ui.STOP_TIME_OUT_MILLS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    private val _finishEvent = MutableSharedFlow<Unit>(0)

    val finishEvent: StateFlow<OnBoardingViewData> = _finishEvent
        .map {
            OnBoardingViewData(true)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIME_OUT_MILLS),
            initialValue = OnBoardingViewData(false)
        )


    fun markSplashViewed() {
        viewModelScope.launch {
            dataStoreManager.recordSplashViewed()
            _finishEvent.emit(Unit)
        }
    }

    data class OnBoardingViewData(
        val isFinished: Boolean
    )
}