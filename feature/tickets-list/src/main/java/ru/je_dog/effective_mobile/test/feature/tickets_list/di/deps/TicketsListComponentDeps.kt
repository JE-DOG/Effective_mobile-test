package ru.je_dog.effective_mobile.test.feature.tickets_list.di.deps

import retrofit2.Retrofit
import ru.je_dog.effective_mobile.test.core.feature.di.deps.DaggerComponentDeps
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator

interface TicketsListComponentDeps: DaggerComponentDeps {
    val retrofit: Retrofit
    val coordinator: Coordinator
}