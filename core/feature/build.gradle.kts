plugins {
    id("je_dog.android.library")
    id("je_dog.android.xml")
}

android {
    namespace = "ru.je_dog.effective_mobile.test.core.feature"
}

dependencies {
    implementation( project(":core") )
    implementation( project(":core:domain") )
    implementation( project(":core:data") )
}