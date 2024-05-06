package ru.je_dog.effective_mobile.test.feature.search_tickets.di

import dagger.Component
import ru.je_dog.effective_mobile.test.data.search_tickets.di.SearchTicketsDataModule
import ru.je_dog.effective_mobile.test.feature.search_tickets.SearchTicketsFragment
import ru.je_dog.effective_mobile.test.feature.search_tickets.di.deps.SearchTicketsComponentDeps

@SearchTicketsComponentScope
@Component(
    modules = [
        SearchTicketsDataModule::class
    ],
    dependencies = [
        SearchTicketsComponentDeps::class
    ]
)
interface SearchTicketsComponent {
    fun inject(searchTicketsFragment: SearchTicketsFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: SearchTicketsComponentDeps
        ): SearchTicketsComponent
    }
}