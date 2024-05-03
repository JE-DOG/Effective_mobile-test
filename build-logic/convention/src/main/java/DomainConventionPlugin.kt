import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.*

class DomainConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        dependencies {
            implementationProject(":core")
            implementationProject(":core:domain")
        }
    }
}