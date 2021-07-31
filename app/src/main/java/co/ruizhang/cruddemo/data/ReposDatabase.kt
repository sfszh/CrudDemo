package co.ruizhang.cruddemo.data


import co.ruizhang.cruddemo.CrudDemoQueries
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ReposDatabase {
    fun getRepos(): Flow<List<Repository>>

    suspend fun insertRepo(repo: Repository)

    suspend fun delete(repo: Repository)
}

class ReposDatabaseImpl constructor(
    private val queries: CrudDemoQueries,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ReposDatabase {
    override fun getRepos(): Flow<List<Repository>> {
        return queries.selectAll { id, name, fullName, description, url, stargazersCount, forksCount ->
            Repository(
                id.toInt(),
                name,
                fullName,
                description,
                url,
                stargazersCount.toInt(),
                forksCount.toInt()
            )
        }.asFlow().mapToList()
    }

    override suspend fun insertRepo(repo: Repository) {
        return withContext(dispatcher) {
            queries.insertRepo(
                repo.id.toLong(),
                repo.name,
                repo.fullName,
                repo.description,
                repo.url,
                repo.stargazersCount.toLong(),
                repo.forksCount.toLong()
            )
        }
    }

    override suspend fun delete(repo: Repository) {
        return withContext(dispatcher) {
            queries.deleteById(repo.id.toLong())
        }
    }
}