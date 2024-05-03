import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.*
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName

class FeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        with(pluginManager){
            applyIfNotFind("je_dog.android.xml")
        }

        dependencies {
            with(libs){
                with(DependenciesName){
                    implementation(findLibrary(coroutines_core))
                }
            }

            implementationProject(":core")
            implementationProject(":core:feature")
            implementationProject(":core:data")
            implementationProject(":core:domain")
        }
    }
}