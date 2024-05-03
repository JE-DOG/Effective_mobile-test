package ru.je_dog.effective_mobile.test.domain.main_screen

import ru.je_dog.effective_mobile.test.core.domain.model.Offer

interface OfferRepository {

    fun getOffers(): List<Offer>

}