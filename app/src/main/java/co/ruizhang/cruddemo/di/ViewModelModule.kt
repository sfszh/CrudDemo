package co.ruizhang.cruddemo.di

import co.ruizhang.cruddemo.ui.onboarding.OnboardingViewModel
import co.ruizhang.cruddemo.ui.repodetail.RepoDetailViewModel
import co.ruizhang.cruddemo.ui.repos.RepoListViewModel
import co.ruizhang.cruddemo.ui.reposearch.RepoSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OnboardingViewModel(get()) }
    viewModel { RepoDetailViewModel(get()) }
    viewModel { RepoListViewModel(get(), get()) }
    viewModel { RepoSearchViewModel(get(), get()) }
}