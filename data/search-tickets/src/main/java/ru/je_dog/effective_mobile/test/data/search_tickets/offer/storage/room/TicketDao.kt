package ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.je_dog.effective_mobile.test.core.data.model.ticket.TicketEntity

@Dao
interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTickets(tickets: List<TicketEntity>)

    @Query("SELECT * FROM ${TicketEntity.TABLE_NAME}")
    suspend fun getTickets(): List<TicketEntity>

}