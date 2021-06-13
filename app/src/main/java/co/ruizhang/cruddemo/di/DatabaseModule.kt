package co.ruizhang.cruddemo.di

import android.content.Context
import androidx.room.Room
import co.ruizhang.cruddemo.data.AppDatabase
import co.ruizhang.cruddemo.data.RepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideRepoDao(appDatabase: AppDatabase) : RepoDao {
        return appDatabase.repoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDatabase"
        ).build()
    }
}