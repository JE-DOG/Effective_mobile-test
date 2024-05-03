package ru.je_dog.effective_mobile.test.feature.main_screen.di.deps

import android.content.Context
import retrofit2.Retrofit
import ru.je_dog.effective_mobile.test.core.feature.di.deps.DaggerComponentDeps

interface MainScreenComponentDeps: DaggerComponentDeps {
    val context: Context
    val retrofit: Retrofit
}