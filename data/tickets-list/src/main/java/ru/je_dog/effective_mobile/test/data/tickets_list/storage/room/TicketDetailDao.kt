package ru.je_dog.effective_mobile.test.data.tickets_list.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.je_dog.effective_mobile.test.core.data.model.ticket.detail.TicketDetailEntity
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

@Dao
interface TicketDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTicketsDetail(ticketsDetail: List<TicketDetailEntity>)

    @Query("SELECT * FROM ${TicketDetailEntity.TABLE_NAME}")
    suspend fun getTickets(): List<TicketDetailEntity>

}