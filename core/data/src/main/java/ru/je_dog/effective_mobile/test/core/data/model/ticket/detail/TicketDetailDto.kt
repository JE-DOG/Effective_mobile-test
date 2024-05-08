package ru.je_dog.effective_mobile.test.core.data.model.ticket.detail

import com.google.gson.annotations.SerializedName
import ru.je_dog.effective_mobile.test.core.data.model.offer.PriceDto
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

/*
{
  "id": 100,
  "badge": "Самый удобный",
  "price": {
    "value": 1999
  },
  "provider_name": "На сайте Купибилет",
  "company": "Якутия",
  "departure": {
    "town": "Москва",
    "date": "2024-02-23T03:15:00",
    "airport": "VKO"
  },
  "arrival": {
    "town": "Сочи",
    "date": "2024-02-23T07:10:00",
    "airport": "AER"
  },
  "has_transfer": false,
  "has_visa_transfer": false,
  "luggage": {
    "has_luggage": false,
    "price": {
      "value": 1082
    }
  },
  "hand_luggage": {
    "has_hand_luggage": true,
    "size": "55x20x40"
  },
  "is_returnable": false,
  "is_exchangable": true
},
**/
data class TicketDetailDto(
    val id: Int,
    val badge: String? = null,
    val price: PriceDto,
    @SerializedName("has_transfer")
    val hasTransfer: Boolean = false,
    val departure: FlightDto,
    val arrival: FlightDto,
)

fun TicketDetailDto.toDomain() = TicketDetail(
    id = id,
    badge = badge,
    price = price.value,
    hasTransfer = hasTransfer,
    departure = departure.toDomain(),
    arrival = arrival.toDomain()
)