plugins {
    id("je_dog.android.library")
    id("je_dog.feature")
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.effective_mobile.test.feature.search_tickets"
}

dependencies {
    implementation(projects.data.searchTickets)
    implementation(projects.domain.searchTickets)
}