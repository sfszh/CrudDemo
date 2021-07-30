package co.ruizhang.cruddemo.data

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class RepoApi(
    private val client: HttpClient,
    private val baseUrl: String = "api.github.com"
) {

    suspend fun searchRepo(query: String): SearchResponse {
        return client.request {
            url("https://${baseUrl}/search/repositories?q=${query}")
            method = HttpMethod.Get
            headers {
                append("Accept", "application/json")
                append("Authorization", "oauth token")
            }
        }
    }

    suspend fun getRepo(fullName: String): ApiRepository {
        return client.request {
            url("https://${baseUrl}/repos/${fullName}")
            method = HttpMethod.Get
            headers {
                append("Accept", "application/json")
                append("Authorization", "oauth token")
            }
        }
    }
}

@Serializable
data class ApiRepository(
    val id: Int,
    val name: String,
    val full_name: String,
    val description: String?,
    val url: String,
    val stargazers_count: Int,
    val forks_count: Int
)

@Serializable
data class SearchResponse(
    val items: List<ApiRepository>
)