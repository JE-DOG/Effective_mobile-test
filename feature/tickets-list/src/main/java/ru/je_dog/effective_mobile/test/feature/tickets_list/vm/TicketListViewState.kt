package ru.je_dog.effective_mobile.test.feature.tickets_list.vm

import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

data class TicketListViewState(
    val ticketsDetail: List<TicketDetail> = emptyList()
)