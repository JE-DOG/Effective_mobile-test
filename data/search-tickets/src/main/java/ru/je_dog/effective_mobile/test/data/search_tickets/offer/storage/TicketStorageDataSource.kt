package ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage

import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

interface TicketStorageDataSource {
    suspend fun addTickets(tickets: List<Ticket>)
    suspend fun getTickets(): List<Ticket>
}