package co.ruizhang.cruddemo.di

import co.ruizhang.cruddemo.data.RepoAPI
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.ReposRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideReposRepository( api : RepoAPI) : ReposRepository {
        return ReposRepositoryImpl(repoApi = api)
    }
}