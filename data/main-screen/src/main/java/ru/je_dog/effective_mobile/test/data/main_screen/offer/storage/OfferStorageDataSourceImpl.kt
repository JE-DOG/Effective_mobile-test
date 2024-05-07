package ru.je_dog.effective_mobile.test.data.main_screen.offer.storage

import ru.je_dog.effective_mobile.test.core.data.model.offer.entity.toDomain
import ru.je_dog.effective_mobile.test.core.data.model.offer.entity.toEntity
import ru.je_dog.effective_mobile.test.core.domain.model.Offer
import ru.je_dog.effective_mobile.test.data.main_screen.offer.storage.room.OfferDao

class OfferStorageDataSourceImpl(
    private val offerDao: OfferDao
): OfferStorageDataSource {

    override suspend fun setOffers(offers: List<Offer>) {
        offerDao.deleteTable()
        offerDao.addOffers(
            offers = offers.map { it.toEntity() }
        )
    }

    override suspend fun getOffers(): List<Offer> {
        return offerDao.getOffers().map { it.toDomain() }
    }
}