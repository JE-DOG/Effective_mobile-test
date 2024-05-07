package ru.je_dog.effective_mobile.test.data.tickets_list.network

import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

interface TicketDetailNetworkDataSource {

    suspend fun getTicketsDetail(): List<TicketDetail>

}