package ru.je_dog.effective_mobile.test.di

import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import ru.je_dog.effective_mobile.test.MainActivity
import ru.je_dog.effective_mobile.test.core.di.CommonModule

@AppComponentScope
@Component(
    modules = [
        CommonModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance
            router: Router
        ): AppComponent
    }
}