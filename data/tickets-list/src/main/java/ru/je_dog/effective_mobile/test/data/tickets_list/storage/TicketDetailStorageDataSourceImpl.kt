package ru.je_dog.effective_mobile.test.data.tickets_list.storage

import ru.je_dog.effective_mobile.test.core.data.model.ticket.detail.toDomain
import ru.je_dog.effective_mobile.test.core.data.model.ticket.detail.toEntity
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail
import ru.je_dog.effective_mobile.test.data.tickets_list.storage.room.TicketDetailDao

class TicketDetailStorageDataSourceImpl(
    private val ticketDetailDao: TicketDetailDao,
): TicketDetailStorageDataSource {
    override suspend fun addTicketsDetail(ticketsDetail: List<TicketDetail>) {
        ticketDetailDao.addTicketsDetail(
            ticketsDetail
                .map { it.toEntity() }
        )
    }

    override suspend fun getTicketsDetail(): List<TicketDetail> {
        return ticketDetailDao.getTickets()
            .map { it.toDomain() }
    }
}