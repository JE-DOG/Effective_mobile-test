package ru.je_dog.effective_mobile.test.data.main_screen.offer.network

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.je_dog.effective_mobile.test.core.data.model.offer.toDomain
import ru.je_dog.effective_mobile.test.core.domain.model.Offer

internal class OfferNetworkDataSourceImpl(
    private val offerApi: OfferApi
): OfferNetworkDataSource {

    override suspend fun getOffers(): List<Offer> {
        val networkOffers = offerApi.getOffers().offers
            .map {  offerDto ->
                offerDto.toDomain()
            }

        return networkOffers
    }

}