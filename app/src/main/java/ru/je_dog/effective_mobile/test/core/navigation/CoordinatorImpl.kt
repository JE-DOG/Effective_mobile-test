package ru.je_dog.effective_mobile.test.core.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.feature.placeholder.PlaceholderFragment

class CoordinatorImpl(
    private val router: Router
): Coordinator {

    override fun navigateToPlaceholder(screenName: String) {
        val screen = getPlaceholderScreen(screenName)
        router.replaceScreen(screen)
    }

    override fun navigateToAviaTickets() {
        val screen = getPlaceholderScreen("Авиабилеты")
        router.replaceScreen(screen)
    }

    private fun getPlaceholderScreen(screenName: String): FragmentScreen {
        return FragmentScreen {
            PlaceholderFragment.create(screenName)
        }
    }
}