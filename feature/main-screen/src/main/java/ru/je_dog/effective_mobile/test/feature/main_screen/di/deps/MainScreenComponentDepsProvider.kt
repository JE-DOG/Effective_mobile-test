package ru.je_dog.effective_mobile.test.feature.main_screen.di.deps

import ru.je_dog.effective_mobile.test.core.feature.di.deps.DaggerComponentDepsProvider
import kotlin.properties.Delegates

object MainScreenComponentDepsProvider: DaggerComponentDepsProvider<MainScreenComponentDeps> {
    override var deps: MainScreenComponentDeps by Delegates.notNull()
}