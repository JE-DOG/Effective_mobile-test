plugins {
    id("je_dog.android.library")
    id("je_dog.feature")
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.effective_mobile.test.feature.main_screen"
}

dependencies {
    with(projects){
        implementation(domain.mainScreen)
        implementation(data.mainScreen)
    }
}