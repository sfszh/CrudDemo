package co.ruizhang.cruddemo.di

import android.content.Context
import co.ruizhang.cruddemo.DataStoreManager
import org.koin.dsl.module


val managerModule = module {
    single { provideDataStoreManager(get()) }
}

private fun provideDataStoreManager(appContext: Context): DataStoreManager {
    return DataStoreManager(appContext)
}