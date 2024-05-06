package ru.je_dog.effective_mobile.test.feature.search_tickets.di.deps

import ru.je_dog.effective_mobile.test.core.feature.di.deps.DaggerComponentDepsProvider
import kotlin.properties.Delegates

object SearchTicketsComponentDepsProvider: DaggerComponentDepsProvider<SearchTicketsComponentDeps> {
    override var deps: SearchTicketsComponentDeps by Delegates.notNull()
}