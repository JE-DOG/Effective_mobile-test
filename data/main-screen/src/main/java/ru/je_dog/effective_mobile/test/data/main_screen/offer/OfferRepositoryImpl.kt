package ru.je_dog.effective_mobile.test.data.main_screen.offer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.effective_mobile.test.core.domain.model.Offer
import ru.je_dog.effective_mobile.test.data.main_screen.offer.network.OfferNetworkDataSource
import ru.je_dog.effective_mobile.test.data.main_screen.offer.storage.OfferStorageDataSource
import ru.je_dog.effective_mobile.test.domain.main_screen.OfferRepository

class OfferRepositoryImpl(
    private val networkDataSource: OfferNetworkDataSource,
    private val storageDataSource: OfferStorageDataSource,
): OfferRepository {
    override fun getOffers(): Flow<List<Offer>> = flow {
        val storageOffers = storageDataSource.getOffers()
        emit(storageOffers)
        val networkOffers = networkDataSource.getOffers()
        if (storageOffers != networkOffers){
            emit(networkOffers)
            storageDataSource.setOffers(networkOffers)
        }
    }
}