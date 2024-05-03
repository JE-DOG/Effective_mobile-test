package ru.je_dog.effective_mobile.test

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import ru.je_dog.effective_mobile.test.di.DaggerAppComponent
import ru.je_dog.effective_mobile.test.feature.main_screen.di.deps.MainScreenComponentDepsProvider

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(
                router = cicerone.router,
                context = this,
            )
    }
    private val cicerone = Cicerone.create()
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDi()
    }

    private fun initDi() {
        with(appComponent){
            MainScreenComponentDepsProvider.deps = this
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}