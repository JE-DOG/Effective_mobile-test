package ru.je_dog.effective_mobile.test.core.data.model.ticket

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.je_dog.effective_mobile.test.core.data.model.offer.PriceDto
import ru.je_dog.effective_mobile.test.core.data.room_utiil.StringListConvertor
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

@TypeConverters(StringListConvertor::class)
@Entity(
    tableName = TicketEntity.TABLE_NAME
)
data class TicketEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    @ColumnInfo("time_range")
    val timeRange: List<String>,
    @Embedded
    val price: PriceDto,
){
    companion object {
        const val TABLE_NAME = "ticket_table"
    }
}

fun TicketEntity.toDomain() = Ticket(
    id = id,
    title = title,
    timeRange = timeRange,
    price = price.value
)

fun Ticket.toEntity() = TicketEntity(
    id = id,
    title = title,
    timeRange = timeRange,
    price = PriceDto(price)
)