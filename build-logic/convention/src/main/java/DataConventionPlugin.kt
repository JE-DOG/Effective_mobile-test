import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.*
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName


class DataConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        with(pluginManager){
            applyIfNotFind("org.jetbrains.kotlin.kapt")
        }
        extensions.configure<KaptExtension> {
            arguments {arg("room.schemaLocation", "$projectDir/schemas")}
        }

        dependencies {
            with(DependenciesName){
                with(libs){
                    implementation(findLibrary(retrofit))
                    implementation(findLibrary(retrofit_converter))
                    implementation(findLibrary(coroutines_core))
                    implementation(findLibrary(room))
                    implementation(findLibrary(room_ktx))
                    kapt(findLibrary(room_compiler))
                }
            }

            implementationProject(":core")
            implementationProject(":core:data")
            implementationProject(":core:domain")
        }
    }
}