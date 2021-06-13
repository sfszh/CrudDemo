package co.ruizhang.cruddemo.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Query("SELECT * FROM repo_table")
    fun getRepos(): Flow<List<Repository>>

    @Insert
    suspend fun insertRepos(repos: List<Repository>)

    @Insert
    suspend fun insertRepo(vararg repos: Repository)

    @Delete
    suspend fun delete(repo: Repository)
}