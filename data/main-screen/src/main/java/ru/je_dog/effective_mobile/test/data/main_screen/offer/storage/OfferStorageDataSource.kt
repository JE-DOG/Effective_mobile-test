package ru.je_dog.effective_mobile.test.data.main_screen.offer.storage

import ru.je_dog.effective_mobile.test.core.domain.model.Offer

interface OfferStorageDataSource {
    suspend fun setOffers(offers: List<Offer>)
    suspend fun getOffers(): List<Offer>
}