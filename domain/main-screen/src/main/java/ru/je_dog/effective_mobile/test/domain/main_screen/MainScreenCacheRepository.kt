package ru.je_dog.effective_mobile.test.domain.main_screen

interface MainScreenCacheRepository {

    fun setLastUserCityInput(cityName: String)

    fun getLastUserCityInput(): String

}