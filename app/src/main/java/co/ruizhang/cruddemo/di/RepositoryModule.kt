package co.ruizhang.cruddemo.di

import co.ruizhang.cruddemo.data.*
import org.koin.dsl.module

val repositoryModule = module {
    single<ReposRepository> { ReposRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<RepoDetailRepository> { RepoDetailRepositoryImpl(get()) }
}