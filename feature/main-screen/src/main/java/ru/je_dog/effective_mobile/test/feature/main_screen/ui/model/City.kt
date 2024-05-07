package ru.je_dog.effective_mobile.test.feature.main_screen.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    @DrawableRes
    val image: Int,
    @StringRes
    val name: Int,
)
