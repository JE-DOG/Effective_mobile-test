package ru.je_dog.effective_mobile.test.data.search_tickets.offer.network

import retrofit2.http.GET
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.model.TicketResponse

interface TicketApi {

    @GET("download?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
    suspend fun getTickets(): TicketResponse

}