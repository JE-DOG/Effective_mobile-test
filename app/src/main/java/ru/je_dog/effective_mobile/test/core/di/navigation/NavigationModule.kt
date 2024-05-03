package ru.je_dog.effective_mobile.test.core.di.navigation

import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.core.navigation.CoordinatorImpl
import ru.je_dog.effective_mobile.test.di.AppComponentScope

@Module
class NavigationModule {

    @Provides
    @AppComponentScope
    fun provideCoordinator(
        router: Router
    ): Coordinator {
        return CoordinatorImpl(
            router
        )
    }

}