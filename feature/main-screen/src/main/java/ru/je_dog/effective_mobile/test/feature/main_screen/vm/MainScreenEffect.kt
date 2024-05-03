package ru.je_dog.effective_mobile.test.feature.main_screen.vm

internal sealed interface MainScreenEffect {

    data class ShowToast(
        val message: String,
    ): MainScreenEffect

}