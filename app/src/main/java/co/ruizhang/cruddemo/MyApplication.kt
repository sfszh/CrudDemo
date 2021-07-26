package co.ruizhang.cruddemo

import android.app.Application
import co.ruizhang.cruddemo.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    databaseModule,
                    managerModule,
                )
            )
        }
    }
}
