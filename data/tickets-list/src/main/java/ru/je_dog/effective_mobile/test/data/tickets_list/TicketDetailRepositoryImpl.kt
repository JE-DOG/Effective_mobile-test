package ru.je_dog.effective_mobile.test.data.tickets_list

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailNetworkDataSource
import ru.je_dog.effective_mobile.test.domain.tickets_list.TicketDetailRepository

class TicketDetailRepositoryImpl(
    private val ticketDetailNetworkDataSource: TicketDetailNetworkDataSource
): TicketDetailRepository {

    override fun getTicketsDetail(): Flow<List<TicketDetail>> = flow {
        emit(ticketDetailNetworkDataSource.getTicketsDetail())
    }

}