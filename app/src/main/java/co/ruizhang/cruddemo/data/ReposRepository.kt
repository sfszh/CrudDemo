package co.ruizhang.cruddemo.data

interface ReposRepository {
    suspend fun getRepos(): List<Repository>
}

class ReposRepositoryImpl(private val api: API) : ReposRepository {
    override suspend fun getRepos(): List<Repository> {
        val apiModel = api.getRepos(username = "octocat").body() ?: emptyList()
        return apiModel.map {
            Repository(
                id = it.id,
                name = it.name,
                url = it.url
            )
        }
    }
}