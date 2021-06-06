package co.ruizhang.cruddemo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ReposRepository {
    suspend fun getRepos(): List<Repository>
}

class ReposRepositoryImpl(private val repoApi: RepoAPI) : ReposRepository {
    override suspend fun getRepos(): List<Repository>  =
        withContext(Dispatchers.IO) {
            val apiModel = repoApi.getRepos(username = "octocat").body() ?: emptyList()
            apiModel.map {
                Repository(
                    id = it.id,
                    name = it.name,
                    url = it.url
                )
            }
        }

}