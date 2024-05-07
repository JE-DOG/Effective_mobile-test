package ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail

import java.time.LocalDateTime

data class Flight(
    val date: LocalDateTime,
    val airport: String,
)