package ru.je_dog.effective_mobile.test.domain.tickets_list

import kotlinx.coroutines.flow.Flow
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

interface TicketDetailRepository {

    fun getTicketsDetail(): Flow<List<TicketDetail>>

}