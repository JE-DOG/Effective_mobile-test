package ru.je_dog.effective_mobile.test.feature.search_tickets.di.deps

import android.content.Context
import retrofit2.Retrofit
import ru.je_dog.effective_mobile.test.core.feature.di.deps.DaggerComponentDeps
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator

interface SearchTicketsComponentDeps: DaggerComponentDeps {
    val retrofit: Retrofit
    val coordinator: Coordinator
    val context: Context
}