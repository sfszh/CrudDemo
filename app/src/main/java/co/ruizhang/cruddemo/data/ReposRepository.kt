package co.ruizhang.cruddemo.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ReposRepository {
    suspend fun getRepos(): List<Repository>
    suspend fun removeRepos(repo : Repository) : List<Repository>
    suspend fun addRepos(repo : Repository) : List<Repository>
}

class ReposRepositoryImpl(private val repoApi: RepoAPI) : ReposRepository {

    private val cache : MutableList<Repository> = mutableListOf()
    override suspend fun getRepos(): List<Repository>  =
        withContext(Dispatchers.IO) {
            cache
        }



    override suspend fun removeRepos(repo: Repository): List<Repository> {
        cache.remove(repo)
        return cache
    }

    override suspend fun addRepos(repo: Repository): List<Repository> {
        cache.add(repo)
        return cache
    }


}

//val apiModel = repoApi.getRepos(username = "octocat").body() ?: emptyList()
//apiModel.map {
//    Repository(
//        id = it.id,
//        name = it.name,
//        description =it.description,
//        url = it.url,
//        stargazersCount = it.stargazers_count,
//        forksCount = it.forks_count
//    )
//}