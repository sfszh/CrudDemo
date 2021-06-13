package co.ruizhang.cruddemo.di

import co.ruizhang.cruddemo.data.*
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
    fun provideReposRepository(repoDao: RepoDao): ReposRepository {
        return ReposRepositoryImpl(repoDao = repoDao)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(api: RepoAPI): SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideRepoDetailRepository(api: RepoAPI): RepoDetailRepository {
        return RepoDetailRepositoryImpl(api)
    }
}