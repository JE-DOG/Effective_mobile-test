package ru.je_dog.effective_mobile.test.data.tickets_list

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailNetworkDataSource
import ru.je_dog.effective_mobile.test.data.tickets_list.storage.TicketDetailStorageDataSource
import ru.je_dog.effective_mobile.test.domain.tickets_list.TicketDetailRepository

class TicketDetailRepositoryImpl(
    private val ticketDetailNetworkDataSource: TicketDetailNetworkDataSource,
    private val storageDataSource: TicketDetailStorageDataSource,
): TicketDetailRepository {

    override fun getTicketsDetail(): Flow<List<TicketDetail>> = flow {
        val storageTicketsDetail = storageDataSource.getTicketsDetail()

        emit(storageTicketsDetail)

        runCatching {
            val networkTicketsDetail = ticketDetailNetworkDataSource.getTicketsDetail()

            if (storageTicketsDetail != networkTicketsDetail){
                emit(networkTicketsDetail)
                storageDataSource.addTicketsDetail(networkTicketsDetail)
            }
        }
    }

}