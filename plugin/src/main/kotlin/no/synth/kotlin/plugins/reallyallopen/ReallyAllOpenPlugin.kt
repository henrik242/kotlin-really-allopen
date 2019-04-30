package no.synth.kotlin.plugins.reallyallopen

import com.google.auto.service.AutoService
import org.apache.logging.log4j.LogManager
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.AbstractCompile
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.CompilerConfigurationKey
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Modality.FINAL
import org.jetbrains.kotlin.descriptors.Modality.OPEN
import org.jetbrains.kotlin.extensions.DeclarationAttributeAltererExtension
import org.jetbrains.kotlin.extensions.DeclarationAttributeAltererExtension.Companion.registerExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinGradleSubplugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption
import org.jetbrains.kotlin.lexer.KtTokens.FINAL_KEYWORD
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.resolve.BindingContext

const val groupId = "no.synth.kotlin.plugins"
const val artifactId = "kotlin-really-allopen"
const val version = "0.2-SNAPSHOT"

open class ReallyAllOpenGradlePlugin : Plugin<Project> {

    var enabled = true

    override fun apply(project: Project) {
        project.extensions.create(artifactId, this::class.java)
    }
}

@AutoService(KotlinGradleSubplugin::class)
class ReallyAllOpenGradleSubplugin : KotlinGradleSubplugin<AbstractCompile> {
    override fun apply(
            project: Project,
            kotlinCompile: AbstractCompile,
            javaCompile: AbstractCompile?,
            variantData: Any?,
            androidProjectHandler: Any?,
            kotlinCompilation: KotlinCompilation<KotlinCommonOptions>?
    ): List<SubpluginOption> {

        val extension = project.extensions
                .findByType(ReallyAllOpenGradlePlugin::class.java)
                ?: ReallyAllOpenGradlePlugin()

        val enabledOption = SubpluginOption(key = "enabled", value = extension.enabled.toString())
        return listOf(enabledOption)
    }

    override fun isApplicable(project: Project, task: AbstractCompile) =
            project.plugins.hasPlugin(ReallyAllOpenGradlePlugin::class.java)

    // Needs to match the key for CommandLineProcessor.pluginId
    override fun getCompilerPluginId(): String = "$groupId.$artifactId"

    override fun getPluginArtifact(): SubpluginArtifact = SubpluginArtifact(
            groupId = groupId,
            artifactId = artifactId,
            version = version
    )
}

@AutoService(ComponentRegistrar::class)
class ReallyAllOpenRegistrar : ComponentRegistrar {

    override fun registerProjectComponents(project: MockProject, configuration: CompilerConfiguration) {
        registerExtension(project, ReallyAllOpenExtension())
    }
}

class ReallyAllOpenExtension : DeclarationAttributeAltererExtension {

    override fun refineDeclarationModality(
            modifierListOwner: KtModifierListOwner,
            declaration: DeclarationDescriptor?,
            containingDeclaration: DeclarationDescriptor?,
            currentModality: Modality,
            bindingContext: BindingContext,
            isImplicitModality: Boolean
    ): Modality? {

        log.error("Hello?")

        return if (currentModality != FINAL) {
            null
        } else if (!isImplicitModality && modifierListOwner.hasModifier(FINAL_KEYWORD)) {
            FINAL // Explicit final
        } else {
            OPEN
        }
    }

    companion object {
        val log = LogManager.getLogger(ReallyAllOpenExtension::class.java)
    }
}

@AutoService(CommandLineProcessor::class)
class ReallyAllOpenCommandLineProcessor : CommandLineProcessor {

    // Needs to match KotlinGradleSubplugin#getCompilerPluginId
    override val pluginId: String = "$groupId.$artifactId"

    override val pluginOptions: Collection<CliOption> = listOf(
            CliOption(
                    optionName = "enabled",
                    valueDescription = "<true|false>",
                    description = "whether to enable the plugin or not",
                    required = false
            )
    )

    override fun processOption(option: AbstractCliOption, value: String, configuration: CompilerConfiguration) =
            when (option.optionName) {
                "enabled" -> configuration.put(KEY_ENABLED, value.toBoolean())
                else -> configuration.put(KEY_ENABLED, true)
            }

    companion object {
        val KEY_ENABLED = CompilerConfigurationKey<Boolean>("whether the plugin is enabled")
    }
}