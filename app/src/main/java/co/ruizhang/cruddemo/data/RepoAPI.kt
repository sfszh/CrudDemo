package co.ruizhang.cruddemo.data

import com.google.gson.annotations.Expose
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoAPI {
    @GET("/users/{username}/repos?type=owner")
    suspend fun getRepos(
        @Path("username") username: String,
    ): Response<List<ApiRepository>>

    @GET("/search/repositories")
    suspend fun searchRepo( @Query("q")query: String): Response<SearchResponse>

    @GET("/repos/{full_name}")
    suspend fun  getRepo(@Path("full_name") fullName: String): Response<ApiRepository>
}

data class ApiRepository(
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val full_name: String,
    @Expose
    val description: String?,
    @Expose
    val url: String,
    @Expose
    val stargazers_count: Int,
    @Expose
    val forks_count: Int
)

data class SearchResponse(
    @Expose
    val items: List<ApiRepository>
)