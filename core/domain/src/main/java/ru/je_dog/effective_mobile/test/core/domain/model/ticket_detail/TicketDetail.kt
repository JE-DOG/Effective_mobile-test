package ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail

data class TicketDetail(
    val id: Int,
    val badge: String? = null,
    val price: Int,
    val hasTransfer: Boolean = false,
    val departure: Flight,
    val arrival: Flight,
)