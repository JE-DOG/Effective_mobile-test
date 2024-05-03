import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.*
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName


class DataConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        dependencies {
            with(DependenciesName){
                with(libs){
                    implementation(findLibrary(retrofit))
                    implementation(findLibrary(retrofit_converter))
                }
            }

            implementationProject(":core")
            implementationProject(":core:data")
            implementationProject(":core:domain")
        }
    }
}