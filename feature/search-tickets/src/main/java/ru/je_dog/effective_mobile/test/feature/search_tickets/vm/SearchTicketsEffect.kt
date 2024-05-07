package ru.je_dog.effective_mobile.test.feature.search_tickets.vm

sealed interface SearchTicketsEffect {

    data class ShowToast(
        val message: String
    ): SearchTicketsEffect

}