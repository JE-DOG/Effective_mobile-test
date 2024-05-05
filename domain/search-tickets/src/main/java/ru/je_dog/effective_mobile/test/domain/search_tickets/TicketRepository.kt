package ru.je_dog.effective_mobile.test.domain.search_tickets

import kotlinx.coroutines.flow.Flow
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

interface TicketRepository {

    fun getTickets(): Flow<List<Ticket>>

}