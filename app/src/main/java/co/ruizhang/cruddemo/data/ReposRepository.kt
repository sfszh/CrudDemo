package co.ruizhang.cruddemo.data

import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun getRepos(): Flow<List<Repository>>
    suspend fun removeRepos(repo : Repository)
    suspend fun addRepos(repo : Repository)
}

class ReposRepositoryImpl constructor(private val db: ReposDatabase) : ReposRepository {
    override fun getRepos(): Flow<List<Repository>> {
        return db.getRepos()
    }

    override suspend fun removeRepos(repo: Repository) {
        db.delete(repo)
    }

    override suspend fun addRepos(repo: Repository) {
        db.insertRepo(repo)
    }
}