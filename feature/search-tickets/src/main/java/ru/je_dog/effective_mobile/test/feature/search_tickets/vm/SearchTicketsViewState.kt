package ru.je_dog.effective_mobile.test.feature.search_tickets.vm

import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

data class SearchTicketsViewState(
    val tickets: List<Ticket> = emptyList(),
)