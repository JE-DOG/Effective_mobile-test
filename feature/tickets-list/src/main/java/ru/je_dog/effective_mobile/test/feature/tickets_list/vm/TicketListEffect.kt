package ru.je_dog.effective_mobile.test.feature.tickets_list.vm

sealed interface TicketListEffect {

    data class ShowToastText(
        val message: String
    ): TicketListEffect

}