package ru.je_dog.effective_mobile.test.data.main_screen.offer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.effective_mobile.test.core.domain.model.Offer
import ru.je_dog.effective_mobile.test.data.main_screen.offer.network.OfferNetworkDataSource
import ru.je_dog.effective_mobile.test.domain.main_screen.OfferRepository

class OfferRepositoryImpl(
    private val networkDataSource: OfferNetworkDataSource
): OfferRepository {
    override fun getOffers(): Flow<List<Offer>> = flow {
        emit(networkDataSource.getOffers())
    }
}