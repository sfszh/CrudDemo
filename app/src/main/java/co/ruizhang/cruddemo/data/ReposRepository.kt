package co.ruizhang.cruddemo.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReposRepository {
    fun getRepos(): Flow<List<Repository>>
    suspend fun removeRepos(repo : Repository)
    suspend fun addRepos(repo : Repository)
}

class ReposRepositoryImpl @Inject constructor(private val repoDao: RepoDao) : ReposRepository {
    override fun getRepos(): Flow<List<Repository>> {
        return repoDao.getRepos()
    }

    override suspend fun removeRepos(repo: Repository) {
        repoDao.delete(repo)
    }

    override suspend fun addRepos(repo: Repository) {
        repoDao.insertRepo(repo)
    }


}