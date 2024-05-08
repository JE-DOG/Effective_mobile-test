package ru.je_dog.effective_mobile.test.data.search_tickets.offer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketNetworkDataSource
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.TicketStorageDataSource
import ru.je_dog.effective_mobile.test.domain.search_tickets.TicketRepository

class TicketRepositoryImpl(
    private val networkDataSource: TicketNetworkDataSource,
    private val storageDataSource: TicketStorageDataSource,
): TicketRepository {
    override fun getTickets(): Flow<List<Ticket>> = flow {
        val storageTickets = storageDataSource.getTickets()
        emit(storageTickets)

        runCatching {
            val networkTickets = networkDataSource.getTickets()

            if (storageTickets != networkTickets){
                emit(networkTickets)
                storageDataSource.addTickets(networkTickets)
            }
        }
    }
}