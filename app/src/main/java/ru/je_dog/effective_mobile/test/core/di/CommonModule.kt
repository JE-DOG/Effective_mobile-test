package ru.je_dog.effective_mobile.test.core.di

import dagger.Module
import ru.je_dog.effective_mobile.test.core.di.navigation.NavigationModule
import ru.je_dog.effective_mobile.test.core.di.network.NetworkModule

@Module(
    includes = [
        NetworkModule::class,
        NavigationModule::class
    ]
)
class CommonModule