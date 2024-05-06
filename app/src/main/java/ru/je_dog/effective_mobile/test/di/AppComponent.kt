package ru.je_dog.effective_mobile.test.di

import android.content.Context
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import ru.je_dog.effective_mobile.test.MainActivity
import ru.je_dog.effective_mobile.test.core.di.CommonModule
import ru.je_dog.effective_mobile.test.feature.main_screen.di.deps.MainScreenComponentDeps
import ru.je_dog.effective_mobile.test.feature.search_tickets.di.deps.SearchTicketsComponentDeps

@AppComponentScope
@Component(
    modules = [
        CommonModule::class,
    ]
)
interface AppComponent:
    MainScreenComponentDeps,
    SearchTicketsComponentDeps {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            router: Router,
            @BindsInstance
            context: Context,
        ): AppComponent
    }
}