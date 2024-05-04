package ru.je_dog.effective_mobile.test.feature.main_screen.vm

import ru.je_dog.effective_mobile.test.core.domain.model.Offer

internal data class MainScreenViewState(
    val offers: List<Offer> = emptyList(),
)
