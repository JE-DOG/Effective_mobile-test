package ru.je_dog.effective_mobile.test.core.navigation

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.feature.main_screen.MainScreenFragment
import ru.je_dog.effective_mobile.test.feature.placeholder.PlaceholderFragment
import ru.je_dog.effective_mobile.test.feature.search_tickets.SearchTicketsFragment
import ru.je_dog.effective_mobile.test.feature.tickets_list.TicketsListFragment

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
        val screen = getSearchTicketsScreen(
            cityTo = cityTo,
            cityFrom = cityFrom
        )
        router.navigateTo(screen)
    }

    override fun navigateToTicketsList(cityFrom: String, cityTo: String) {
        val screen = getTicketsListScreen(cityFrom = cityFrom, cityTo = cityTo)
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

    override fun back() {
        router.exit()
    }

    private fun getSearchTicketsScreen(
        cityTo: String,
        cityFrom: String,
    ): FragmentScreen {
        return FragmentScreen {
            SearchTicketsFragment.create(
                cityFrom = cityFrom,
                cityTo = cityTo
            )
        }
    }
    private fun getTicketsListScreen(
        cityTo: String,
        cityFrom: String,
    ): FragmentScreen {
        return FragmentScreen {
            TicketsListFragment.create(
                cityFrom = cityFrom,
                cityTo = cityTo
            )
        }
    }
}