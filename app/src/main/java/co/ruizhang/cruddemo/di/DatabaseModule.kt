package co.ruizhang.cruddemo.di

import android.content.Context
import androidx.room.Room
import co.ruizhang.cruddemo.data.AppDatabase
import co.ruizhang.cruddemo.data.RepoDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideAppDatabase(get()) }
    single { provideRepoDao(get()) }
}

fun provideRepoDao(appDatabase: AppDatabase): RepoDao {
    return appDatabase.repoDao()
}

private fun provideAppDatabase(appContext: Context): AppDatabase {
    return Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "AppDatabase"
    ).build()
}