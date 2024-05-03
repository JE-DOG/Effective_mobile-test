package ru.je_dog.effective_mobile.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.core.utill.ext.appComponent
import ru.je_dog.effective_mobile.test.feature.placeholder.PlaceholderFragment
import ru.je_dog.test.effective_mobile.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var coordinator: Coordinator

    private val navigator = AppNavigator(
        activity = this,
        containerId = R.id.main_container
    )
    private val navigatorHolder = App.instance.navigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coordinator.navigateToPlaceholder("Placeholder")
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.setNavigator(navigator)
        super.onPause()
    }
}