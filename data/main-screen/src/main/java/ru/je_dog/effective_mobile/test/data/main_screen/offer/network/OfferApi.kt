package ru.je_dog.effective_mobile.test.data.main_screen.offer.network

import retrofit2.http.GET
import ru.je_dog.effective_mobile.test.data.main_screen.offer.model.OfferResponse

interface OfferApi {

    @GET("download?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
    suspend fun getOffers(): OfferResponse

}