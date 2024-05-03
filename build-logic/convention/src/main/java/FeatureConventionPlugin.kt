import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import ru.je_dog.effective_mobile.test.build_logic.convention.core.ext.*

class FeatureConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target) {

        with(pluginManager){
            applyIfNotFind("je_dog.android.xml")
        }

        dependencies {

            implementationProject(":core")
            implementationProject(":core:feature")
            implementationProject(":core:data")
            implementationProject(":core:domain")

        }

    }
}