package ru.je_dog.effective_mobile.test.data.tickets_list.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.je_dog.effective_mobile.test.core.data.model.ticket.detail.TicketDetailEntity

@Database(
    entities = [
        TicketDetailEntity::class
    ],
    version = 1
)
abstract class TicketDetailDatabase: RoomDatabase() {
    abstract fun getTicketDetailDao(): TicketDetailDao
}