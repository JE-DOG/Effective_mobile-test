package ru.je_dog.effective_mobile.test.data.search_tickets.offer.network

import ru.je_dog.effective_mobile.test.core.data.model.offer.toDomain
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

internal class TicketNetworkDataSourceImpl(
    private val ticketApi: TicketApi
): TicketNetworkDataSource {

    override suspend fun getTickets(): List<Ticket> {
        val networkOffers = ticketApi.getTickets().ticketsOffers
            .map {  ticketDto ->
                ticketDto.toDomain()
            }

        return networkOffers
    }

}