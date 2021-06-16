package co.ruizhang.cruddemo.di

import android.content.Context
import co.ruizhang.cruddemo.DataStoreManager
import co.ruizhang.cruddemo.data.AppDatabase
import co.ruizhang.cruddemo.data.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {
    @Provides
    fun provideDataStoreManager(@ApplicationContext appContext: Context): DataStoreManager {
        return DataStoreManager(appContext)
    }
}