package co.ruizhang.cruddemo.data

import com.google.gson.annotations.Expose
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoAPI {
    @GET("/users/{username}/repos?type=owner")
    suspend fun getRepos(
        @Path("username") username: String,
    ) : Response<List<ApiRepository>>
}

data class ApiRepository (
    @Expose
    val id: Int,
    @Expose
    val name : String,
    @Expose
    val url : String
)

