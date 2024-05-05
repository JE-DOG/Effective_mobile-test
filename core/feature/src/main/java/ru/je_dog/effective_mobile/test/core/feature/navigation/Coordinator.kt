package ru.je_dog.effective_mobile.test.core.feature.navigation

interface Coordinator {

    fun navigateToPlaceholder(screenName: String)

    fun navigateToAviaTickets()

     fun navigateToSearchTickets(
         cityFrom: String,
         cityTo: String,
     )

}