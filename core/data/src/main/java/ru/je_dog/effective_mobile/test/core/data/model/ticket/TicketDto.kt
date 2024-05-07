package ru.je_dog.effective_mobile.test.core.data.model.ticket

import com.google.gson.annotations.SerializedName
import ru.je_dog.effective_mobile.test.core.data.model.offer.PriceDto
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

data class TicketDto(
    val id: Int,
    val title: String,
    @SerializedName("time_range")
    val timeRange: List<String>,
    val price: PriceDto,
)

fun TicketDto.toDomain() = Ticket(
    id = id,
    title = title,
    timeRange = timeRange,
    price = price.value
)
