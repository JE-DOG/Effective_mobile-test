package ru.je_dog.effective_mobile.test

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import ru.je_dog.effective_mobile.test.di.DaggerAppComponent

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory()
            .create(
                router = cicerone.router,
            )
    }
    private val cicerone = Cicerone.create()
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}