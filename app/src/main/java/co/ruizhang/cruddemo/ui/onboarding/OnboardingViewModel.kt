package co.ruizhang.cruddemo.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.ruizhang.cruddemo.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val dataStoreManager: DataStoreManager) :
    ViewModel() {
    private val _finishEvent = MutableSharedFlow<Unit>(0)

    val finishEvent: StateFlow<OnBoardingUIState> = _finishEvent
        .map {
            OnBoardingUIState(true)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), //https://medium.com/androiddevelopers/things-to-know-about-flows-sharein-and-statein-operators-20e6ccb2bc74
            initialValue = OnBoardingUIState(false)
        )


    fun markSplashViewed() {
        viewModelScope.launch {
            dataStoreManager.recordSplashViewed()
            _finishEvent.emit(Unit)
        }
    }

    data class OnBoardingUIState(
        val isFinished: Boolean
    )
}