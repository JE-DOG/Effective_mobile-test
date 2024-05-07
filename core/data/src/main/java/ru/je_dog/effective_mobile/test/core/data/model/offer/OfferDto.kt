package ru.je_dog.effective_mobile.test.core.data.model.offer

import ru.je_dog.effective_mobile.test.core.domain.model.Offer

/*
Example:
{
  "id": 1,
  "title": "Die Antwoord",
  "town": "Будапешт",
  "price": {
    "value": 5000
  }
}
**/
data class OfferDto(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDto,
)

fun OfferDto.toDomain() = Offer(
        id = id,
        title = title,
        town = town,
        price = price.value
    )