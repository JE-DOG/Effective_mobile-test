package ru.je_dog.effective_mobile.test.data.main_screen.cache

import ru.je_dog.effective_mobile.test.data.main_screen.cache.storage.MainScreenCacheStorageDataSource
import ru.je_dog.effective_mobile.test.domain.main_screen.MainScreenCacheRepository

internal class MainScreenCacheRepositoryImpl(
    private val storageDataSource: MainScreenCacheStorageDataSource
): MainScreenCacheRepository {

    override fun setLastUserCityInput(cityName: String) {
        storageDataSource.setLastUserCityInput(cityName)
    }

    override fun getLastUserCityInput(): String {
        return storageDataSource.getLastUserCityInput()
    }
}