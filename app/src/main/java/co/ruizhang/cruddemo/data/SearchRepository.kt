package co.ruizhang.cruddemo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface   SearchRepository {
    suspend fun search(query: String): List<Repository>
}

class SearchRepositoryImpl(private val repoApi: RepoAPI) : SearchRepository {
    override suspend fun search(query: String): List<Repository> {
        return withContext(Dispatchers.IO) {
            repoApi.searchRepo(query).let { resp ->
                resp.body()?.items?.map {
                    Repository(
                        id = it.id,
                        name = it.name,
                        fullName = it.full_name,
                        description = it.description,
                        url = it.url,
                        stargazersCount = it.stargazers_count,
                        forksCount = it.forks_count
                    )
                } ?: emptyList()
            }
        }
    }
}