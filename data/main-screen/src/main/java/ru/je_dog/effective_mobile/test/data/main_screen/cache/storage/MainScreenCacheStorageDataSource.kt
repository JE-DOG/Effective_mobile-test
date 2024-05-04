package ru.je_dog.effective_mobile.test.data.main_screen.cache.storage

interface MainScreenCacheStorageDataSource {
    fun getLastUserCityInput(): String
    fun setLastUserCityInput(cityName: String)
}