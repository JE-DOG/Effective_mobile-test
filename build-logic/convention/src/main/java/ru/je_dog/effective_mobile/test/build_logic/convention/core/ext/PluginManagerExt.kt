package ru.je_dog.effective_mobile.test.build_logic.convention.core.ext

import org.gradle.api.plugins.PluginManager


fun PluginManager.applyIfNotFind(id: String){

    if (!hasPlugin(id)){
        apply(id)
    }

}