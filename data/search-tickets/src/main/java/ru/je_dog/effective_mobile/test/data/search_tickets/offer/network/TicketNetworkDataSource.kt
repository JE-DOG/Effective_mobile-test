package ru.je_dog.effective_mobile.test.data.search_tickets.offer.network

import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

interface TicketNetworkDataSource {

    suspend fun getTickets(): List<Ticket>

}