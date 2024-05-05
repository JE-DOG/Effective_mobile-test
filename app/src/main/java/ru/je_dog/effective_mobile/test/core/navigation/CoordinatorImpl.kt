package ru.je_dog.effective_mobile.test.core.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.feature.main_screen.MainScreenFragment
import ru.je_dog.effective_mobile.test.feature.placeholder.PlaceholderFragment

class CoordinatorImpl(
    private val router: Router
): Coordinator {

    override fun navigateToPlaceholder(screenName: String) {
        val screen = getPlaceholderScreen(screenName)
        router.navigateTo(screen)
    }

    override fun navigateToAviaTickets() {
        val screen = getMainScreen()
        router.replaceScreen(screen)
    }

    override fun navigateToSearchTickets(cityFrom: String, cityTo: String) {
        val screen = getPlaceholderScreen("City from: $cityFrom\nCity to: $cityTo")
        router.navigateTo(screen)
    }

    private fun getPlaceholderScreen(screenName: String): FragmentScreen {
        return FragmentScreen {
            PlaceholderFragment.create(screenName)
        }
    }
    private fun getMainScreen(): FragmentScreen {
        return FragmentScreen {
            MainScreenFragment()
        }
    }
}