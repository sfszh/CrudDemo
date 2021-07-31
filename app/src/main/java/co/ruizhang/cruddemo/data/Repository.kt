package co.ruizhang.cruddemo.data

data class Repository (
    val id: Int,
    val name : String,
    val fullName : String,
    val description: String?,
    val url : String,
    val stargazersCount: Int,
    val forksCount: Int
    )