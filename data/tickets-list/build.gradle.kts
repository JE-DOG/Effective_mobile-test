plugins {
    id("je_dog.android.library")
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.effective_mobile.test.data.tickets_list"
}

dependencies {
    implementation(projects.domain.ticketsList)
}