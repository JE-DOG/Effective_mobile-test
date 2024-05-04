package ru.je_dog.effective_mobile.test.core.feature.di.deps

interface DaggerComponentDepsProvider<D: DaggerComponentDeps> {
    val deps: D
}