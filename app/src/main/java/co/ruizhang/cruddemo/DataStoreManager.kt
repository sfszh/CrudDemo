package co.ruizhang.cruddemo

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import co.ruizhang.cruddemo.DataStoreManager.Companion.USER_PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(USER_PREFERENCES_NAME)

class DataStoreManager  @Inject constructor(@ApplicationContext appContext: Context) {
    private val userPreferencesDataStore = appContext.dataStore
    private val splashHasViewedKey = booleanPreferencesKey(SPLASH_HAS_VIEWED)
    suspend fun recordSplashViewed() {
        userPreferencesDataStore.edit { preference ->
            preference[splashHasViewedKey] = true
        }
    }

    val hasSplashViewed: Flow<Boolean> = userPreferencesDataStore.data.map { preferences ->
//        preferences[splashHasViewedKey] ?: false
        false
    }

    companion object {
        const val USER_PREFERENCES_NAME = "user_preferences"
        const val SPLASH_HAS_VIEWED = "splash_has_viewed"

    }
}