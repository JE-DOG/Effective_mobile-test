package ru.je_dog.effective_mobile.test.data.tickets_list.network.model

import ru.je_dog.effective_mobile.test.core.data.model.ticket.detail.TicketDetailDto

data class TicketDetailResponse(
    val tickets: List<TicketDetailDto>
)
