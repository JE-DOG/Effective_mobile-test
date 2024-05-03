package ru.je_dog.effective_mobile.test.data.main_screen.offer.network

import retrofit2.http.GET
import ru.je_dog.effective_mobile.test.core.data.model.offer.OfferDto

interface OfferApi {

    @GET("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
    suspend fun getOffers(): List<OfferDto>

}