package ru.je_dog.effective_mobile.test.core.data.model.offer.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.je_dog.effective_mobile.test.core.data.model.offer.PriceDto
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
@Entity(
    tableName = "offer_table"
)
data class OfferEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val town: String,
    @Embedded
    val price: PriceDto,
){
    companion object {
        const val TABLE_NAME = "offer_table"
    }
}

fun OfferEntity.toDomain() = Offer(
    id = id,
    title = title,
    town = town,
    price = price.value
)

fun Offer.toEntity() = OfferEntity(
    id = id,
    title = title,
    town = town,
    price = PriceDto(price),
)