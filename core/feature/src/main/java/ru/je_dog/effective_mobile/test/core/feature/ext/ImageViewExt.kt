package ru.je_dog.effective_mobile.test.core.feature.ext

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat

fun ImageView.setIconColor(
    @ColorRes
    color: Int
){
    DrawableCompat.setTint(
        drawable,
        context.getColor(color)
    )
}