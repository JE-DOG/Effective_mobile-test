import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.applyIfNotFind
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.implementation
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.kapt
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.testImplementation
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.versionCatalog
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.Config
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName

class AndroidApplicationConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        with(pluginManager){
            applyIfNotFind("com.android.application")
            applyIfNotFind("org.jetbrains.kotlin.android")
            applyIfNotFind("org.jetbrains.kotlin.kapt")
            applyIfNotFind("je_dog.android.xml")
        }

        extensions.configure<ApplicationExtension> {

            compileSdk = Config.compileSdk

            defaultConfig {
                applicationId = Config.applicationId
                minSdk = Config.minSdk
                targetSdk = Config.targetSdk
                versionCode = Config.versionCode
                versionName = Config.versionName

                testInstrumentationRunner = Config.testInstrumentationRunner

                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }

            kotlinExtension.jvmToolchain(17)

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

        }

        dependencies {
            with(libs){
                with(DependenciesName){
                    implementation(findLibrary(androidx_core_ktx))
                    implementation(findLibrary(androidx_lifecycle_runtime_ktx))
                    //Dagger
                    implementation(findLibrary(dagger_android))
                    kapt(findLibrary(dagger_compiler))

                    testImplementation(findLibrary(jUnit))
                }
            }
        }
    }
}