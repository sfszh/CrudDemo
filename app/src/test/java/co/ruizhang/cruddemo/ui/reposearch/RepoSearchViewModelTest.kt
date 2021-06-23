package co.ruizhang.cruddemo.ui.reposearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import co.ruizhang.cruddemo.data.MOCK_REPOS
import co.ruizhang.cruddemo.data.ReposRepository
import co.ruizhang.cruddemo.data.Repository
import co.ruizhang.cruddemo.data.SearchRepository
import co.ruizhang.cruddemo.util.MainCoroutineRule
import co.ruizhang.cruddemo.util.getOrAwaitValue
import co.ruizhang.cruddemo.util.observeForTesting
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class RepoSearchViewModelTest {



    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val searchRepoMock = mockk<SearchRepository>()
    val reposRepoMock = mockk<ReposRepository>()



    @FlowPreview
    @ExperimentalCoroutinesApi
    @Test
    fun getSearchViewData_emit0item() = runBlockingTest {
        val searchResult = emptyList<Repository>()

        coEvery { searchRepoMock.search(any()) } returns searchResult
        every { reposRepoMock.getRepos() } returns flow {
            emit(MOCK_REPOS)
        }
        val searchViewModel = RepoSearchViewModel(searchRepoMock, reposRepoMock)
        searchViewModel.setQueryText("foobar")
        searchViewModel.search()
        val result = searchViewModel.searchViewData.getOrAwaitValue()
        assertEquals(result.size, 0)
    }
}