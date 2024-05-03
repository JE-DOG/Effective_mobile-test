import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.applyIfNotFind
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.androidTestImplementation
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.implementation
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.implementationProject
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.testImplementation
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.versionCatalog
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.kapt
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.Config

class AndroidLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        with(pluginManager){
            applyIfNotFind("com.android.library")
            applyIfNotFind("org.jetbrains.kotlin.android")
            applyIfNotFind("org.jetbrains.kotlin.kapt")
        }

        extensions.configure<LibraryExtension> {
            compileSdk = Config.compileSdk

            defaultConfig {
                minSdk = Config.minSdk
                targetSdk = Config.targetSdk

                testInstrumentationRunner = Config.testInstrumentationRunner

                vectorDrawables {
                    useSupportLibrary = true
                }
            }

            kotlinExtension.jvmToolchain(17)

            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildFeatures {
                viewBinding = true
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