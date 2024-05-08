package ru.je_dog.effective_mobile.test.core.data.model.ticket.detail

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ru.je_dog.effective_mobile.test.core.data.model.offer.PriceDto
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

@Entity(
    tableName = TicketDetailEntity.TABLE_NAME
)
data class TicketDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val badge: String? = null,
    @Embedded
    val price: PriceDto,
    @ColumnInfo("has_transfer")
    val hasTransfer: Boolean = false,
    @Embedded("departure")
    val departure: FlightDto,
    @Embedded("arrival")
    val arrival: FlightDto,
){
    companion object {
        const val TABLE_NAME = "ticket_detail_table"
    }
}

fun TicketDetailEntity.toDomain() = TicketDetail(
    id = id,
    badge = badge,
    price = price.value,
    hasTransfer = hasTransfer,
    departure = departure.toDomain(),
    arrival = arrival.toDomain()
)

fun TicketDetail.toEntity() = TicketDetailEntity(
    id = id,
    badge = badge,
    price = PriceDto(price),
    hasTransfer = hasTransfer,
    departure = departure.toDto(),
    arrival = arrival.toDto()
)