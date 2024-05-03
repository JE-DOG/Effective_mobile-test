package ru.je_dog.effective_mobile.test.core.utill.ext

import android.content.Context
import ru.je_dog.effective_mobile.test.App
import ru.je_dog.effective_mobile.test.di.AppComponent

val Context.appComponent: AppComponent
    get() {
        return when(this){
            is App -> {
                appComponent
            }

            else -> {
                (applicationContext as App).appComponent
            }
        }
    }