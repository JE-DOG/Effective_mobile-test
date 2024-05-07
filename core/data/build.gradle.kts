plugins {
    id("je_dog.android.library")
}

android {
    namespace = "ru.je_dog.effective_mobile.test.core.data"
}

dependencies {
    with(projects.core){
        implementation(this)
        implementation(domain)
    }
    implementation( project(":core") )
    implementation( project(":core:domain") )
    with(libs.retrofit){
        implementation(converter)
    }
}