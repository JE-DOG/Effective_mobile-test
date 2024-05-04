package ru.je_dog.effective_mobile.test.data.main_screen.cache.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

internal class MainScreenCacheStorageDataSourceImpl(
    context: Context
): MainScreenCacheStorageDataSource {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)

    override fun getLastUserCityInput(): String {
        return sharedPreferences.getString(USER_LAST_CITY_INPUT_KEY,"") ?: ""
    }

    override fun setLastUserCityInput(cityName: String) {
        sharedPreferences.edit {
            putString(USER_LAST_CITY_INPUT_KEY,cityName)
        }
    }

    companion object {
        private const val SHARED_PREF_NAME = "MainScreenCache"

        private const val USER_LAST_CITY_INPUT_KEY = "USER_LAST_CITY_INPUT_KEY"
    }
}