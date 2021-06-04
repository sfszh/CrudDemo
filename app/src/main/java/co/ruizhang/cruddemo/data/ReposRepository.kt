package co.ruizhang.cruddemo.data

interface ReposRepository {
    suspend fun getRepos(): List<Repository>
}

class ReposRepositoryImpl(private val repoApi: RepoAPI) : ReposRepository {
    override suspend fun getRepos(): List<Repository> {
        val apiModel = repoApi.getRepos(username = "octocat").body() ?: emptyList()
        return apiModel.map {
            Repository(
                id = it.id,
                name = it.name,
                url = it.url
            )
        }
    }
}