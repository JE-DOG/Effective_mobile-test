package ru.je_dog.effective_mobile.test.feature.main_screen.di

import dagger.Component
import ru.je_dog.effective_mobile.test.data.main_screen.di.MainScreenDataModule
import ru.je_dog.effective_mobile.test.feature.main_screen.MainScreenFragment
import ru.je_dog.effective_mobile.test.feature.main_screen.di.deps.MainScreenComponentDeps

@MainScreenComponentScope
@Component(
    modules = [
        MainScreenDataModule::class
    ],
    dependencies = [
        MainScreenComponentDeps::class
    ]
)
internal interface MainScreenComponent {

    fun inject(mainScreenFragment: MainScreenFragment)

    @Component.Factory
    interface Factory {
        fun create(
            mainScreenComponentDeps: MainScreenComponentDeps
        ): MainScreenComponent
    }
}