package co.ruizhang.cruddemo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RepoDetailRepository {
    suspend fun get(fullName: String): Repository?
}

class RepoDetailRepositoryImpl(private val repoApi: RepoApi) : RepoDetailRepository {
    override suspend fun get(fullName: String): Repository? {
        return withContext(Dispatchers.IO) {
            repoApi.getRepo(fullName).let { resp ->
                resp.let {
                    Repository(
                        id = it.id,
                        name = it.name,
                        fullName = it.full_name,
                        description = it.description,
                        url = it.url,
                        stargazersCount = it.stargazers_count,
                        forksCount = it.forks_count
                    )
                }

            }
        }
    }
}

