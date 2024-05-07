package ru.je_dog.effective_mobile.test.data.tickets_list.network

import retrofit2.http.GET
import ru.je_dog.effective_mobile.test.data.tickets_list.network.model.TicketDetailResponse

interface TicketDetailApi {

    @GET("download?id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTicketDetail(): TicketDetailResponse

}