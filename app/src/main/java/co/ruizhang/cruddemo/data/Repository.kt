package co.ruizhang.cruddemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_table")
data class Repository (
    @PrimaryKey
    val id: Int,
    val name : String,
    val fullName : String,
    val description: String?,
    val url : String,
    val stargazersCount: Int,
    val forksCount: Int
    )