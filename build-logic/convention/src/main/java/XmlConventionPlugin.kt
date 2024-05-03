import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.implementation
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.versionCatalog
import ru.je_dog.effective_mobile.test.build_logic.convention.dependencies.DependenciesName

class XmlConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        val libs = versionCatalog()

        dependencies {
            with(libs){
                with(DependenciesName){
                    implementation(findLibrary(xml_appcompat))
                    implementation(findLibrary(xml_material))
                    implementation(findLibrary(xml_constraint_layout))
                    implementation(findLibrary(xml_adapter_delegate))
                    implementation(findLibrary(xml_cicerone))
                }
            }
        }
    }
}