plugins {
    id("je_dog.android.application")
    id("je_dog.data")
}

android {
    namespace = "ru.je_dog.test.effective_mobile"

    defaultConfig {
        buildConfigField("String","BASE_URL","\"https://drive.usercontent.google.com/\"") //https://drive.usercontent.google.com/download?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    with(projects){
        with(core){
            implementation(this)
            implementation(domain)
            implementation(data)
            implementation(feature)
        }
        with(feature){
            implementation(mainScreen)
            implementation(searchTickets)
            implementation(placeholder)
            implementation(ticketsList)
        }
    }
}