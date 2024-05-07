package ru.je_dog.effective_mobile.test.data.tickets_list.network

import ru.je_dog.effective_mobile.test.core.data.model.offer.ticket_details.toDomain
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

class TicketDetailNetworkDataSourceImpl(
    private val ticketDetailApi: TicketDetailApi
): TicketDetailNetworkDataSource {

    override suspend fun getTicketsDetail(): List<TicketDetail> {
        val ticketsDetail = ticketDetailApi.getTicketDetail().tickets
            .map { it.toDomain() }

        return ticketsDetail
    }
}