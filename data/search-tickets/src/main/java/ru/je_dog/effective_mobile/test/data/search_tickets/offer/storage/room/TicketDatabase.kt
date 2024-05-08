package ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.je_dog.effective_mobile.test.core.data.model.ticket.TicketEntity

@Database(
    entities = [
        TicketEntity::class
    ],
    version = 1
)
abstract class TicketDatabase: RoomDatabase() {
    abstract fun getTicketDao(): TicketDao
}