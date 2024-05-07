package ru.je_dog.effective_mobile.test.feature.tickets_list.di

import dagger.Component
import ru.je_dog.effective_mobile.test.data.tickets_list.di.TicketListDataModule
import ru.je_dog.effective_mobile.test.feature.tickets_list.TicketsListFragment
import ru.je_dog.effective_mobile.test.feature.tickets_list.di.deps.TicketsListComponentDeps

@TicketListComponentScope
@Component(
    modules = [
        TicketListDataModule::class
    ],
    dependencies = [
        TicketsListComponentDeps::class
    ]
)
interface TicketsListComponent {
    fun inject(ticketsListFragment: TicketsListFragment)

    @Component.Factory
    interface Factory {
        fun create(
            deps: TicketsListComponentDeps
        ): TicketsListComponent
    }
}