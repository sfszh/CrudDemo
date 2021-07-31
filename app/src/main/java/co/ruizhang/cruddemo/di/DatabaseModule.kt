package co.ruizhang.cruddemo.di

import android.content.Context
import co.ruizhang.cruddemo.CrudDemoQueries
import co.ruizhang.cruddemo.Database
import co.ruizhang.cruddemo.data.ReposDatabase
import co.ruizhang.cruddemo.data.ReposDatabaseImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.dsl.module

val databaseModule = module {
    single { provideAndroidSqlDriver(get()) }
    single { provideQueries(get()) }
    single<ReposDatabase> { ReposDatabaseImpl(get()) }
}

fun provideAndroidSqlDriver(appContext: Context): AndroidSqliteDriver {
    return AndroidSqliteDriver(
        schema = Database.Schema,
        context = appContext,
        name = "items.db"
    )
}

fun provideQueries(androidSqliteDriver: AndroidSqliteDriver): CrudDemoQueries {
    return Database(androidSqliteDriver).crudDemoQueries
}
