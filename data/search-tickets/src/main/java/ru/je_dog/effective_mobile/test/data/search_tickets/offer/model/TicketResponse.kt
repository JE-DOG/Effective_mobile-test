package ru.je_dog.effective_mobile.test.data.search_tickets.offer.model

import com.google.gson.annotations.SerializedName
import ru.je_dog.effective_mobile.test.core.data.model.ticket.TicketDto

data class TicketResponse (
    @SerializedName("tickets_offers")
    val ticketsOffers: List<TicketDto>
)