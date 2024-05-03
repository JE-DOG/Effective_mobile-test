import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.*
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName

class KotlinLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        with(pluginManager){
            applyIfNotFind("org.jetbrains.kotlin.jvm")
            applyIfNotFind("org.jetbrains.kotlin.kapt")
        }

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        kotlinExtension.jvmToolchain(17)

        dependencies {
            with(DependenciesName){
                with(libs){
                    implementation(findLibrary(coroutines_core))
                    testImplementation(findLibrary(jUnit))
                    //Dagger
                    implementation(findLibrary(dagger_core))
                    kapt(findLibrary(dagger_compiler))
                }
            }
        }
    }
}