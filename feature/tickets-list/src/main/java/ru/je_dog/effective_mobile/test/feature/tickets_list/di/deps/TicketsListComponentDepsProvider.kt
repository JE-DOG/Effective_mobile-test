package ru.je_dog.effective_mobile.test.feature.tickets_list.di.deps

import ru.je_dog.effective_mobile.test.core.feature.di.deps.DaggerComponentDepsProvider
import kotlin.properties.Delegates

object TicketsListComponentDepsProvider: DaggerComponentDepsProvider<TicketsListComponentDeps> {
    override var deps: TicketsListComponentDeps by Delegates.notNull()
}