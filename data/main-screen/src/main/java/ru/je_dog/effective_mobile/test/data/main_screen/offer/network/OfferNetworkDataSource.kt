package ru.je_dog.effective_mobile.test.data.main_screen.offer.network

import ru.je_dog.effective_mobile.test.core.domain.model.Offer

interface OfferNetworkDataSource {

    suspend fun getOffers(): List<Offer>

}