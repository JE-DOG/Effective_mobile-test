package ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage

import ru.je_dog.effective_mobile.test.core.data.model.ticket.toDomain
import ru.je_dog.effective_mobile.test.core.data.model.ticket.toEntity
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.room.TicketDao

class TicketStorageDataSourceImpl(
    private val ticketDao: TicketDao,
): TicketStorageDataSource {

    override suspend fun addTickets(tickets: List<Ticket>) {
        ticketDao.addTickets(
            tickets = tickets
                .map { it.toEntity() }
        )
    }

    override suspend fun getTickets(): List<Ticket> {
        return ticketDao.getTickets()
            .map { it.toDomain() }
    }
}