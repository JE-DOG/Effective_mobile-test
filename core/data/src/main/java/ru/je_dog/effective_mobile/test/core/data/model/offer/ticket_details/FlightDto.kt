package ru.je_dog.effective_mobile.test.core.data.model.offer.ticket_details

import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.Flight
import java.time.LocalDateTime

data class FlightDto(
    val date: String,
    val airport: String,
)

fun FlightDto.toDomain() = Flight(
    date = LocalDateTime.parse(date),
    airport = airport
)