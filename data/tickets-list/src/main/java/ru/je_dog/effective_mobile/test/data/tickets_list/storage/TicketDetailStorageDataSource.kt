package ru.je_dog.effective_mobile.test.data.tickets_list.storage

import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

interface TicketDetailStorageDataSource {
    suspend fun addTicketsDetail(ticketsDetail: List<TicketDetail>)

    suspend fun getTicketsDetail(): List<TicketDetail>
}