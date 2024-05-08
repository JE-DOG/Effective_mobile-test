package ru.je_dog.effective_mobile.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.core.feature.network.NetworkMonitor
import ru.je_dog.effective_mobile.test.core.utill.ext.appComponent
import ru.je_dog.effective_mobile.test.feature.placeholder.PlaceholderFragment
import ru.je_dog.test.effective_mobile.R
import ru.je_dog.test.effective_mobile.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var coordinator: Coordinator
    @Inject
    lateinit var networkMonitor: NetworkMonitor
    lateinit var binding: ActivityMainBinding

    private val navigator = AppNavigator(
        activity = this,
        containerId = R.id.main_container
    )
    private val navigatorHolder = App.instance.navigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() = with(binding) {
        with(bottomNav){
            itemIconTintList = null

            coordinator.navigateToAviaTickets()
            setOnItemSelectedListener {  menuItem ->
                when(menuItem.itemId) {
                    R.id.bottom_nav_item_avia_tickets -> {
                        coordinator.navigateToAviaTickets()
                    }

                    else -> {
                        coordinator.navigateToPlaceholder(menuItem.title.toString())
                    }
                }

                return@setOnItemSelectedListener true
            }
        }
        networkMonitor.broadcastNetworkState()
            .onEach { connectionState ->
                Log.d("InternetTag",connectionState.toString())
                if (connectionState){
                    notificationText.visibility = View.GONE
                    notificationText.text = ""
                }else {
                    notificationText.visibility = View.VISIBLE
                    notificationText.text = getString(ru.je_dog.effective_mobile.test.core.feature.R.string.system_check_network_state)
                }
            }
            .launchIn(lifecycleScope)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}