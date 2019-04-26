package no.synth.kotlin.plugins.reallyallopen

import com.google.auto.service.AutoService
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
import org.jetbrains.kotlin.lexer.KtTokens.FINAL_KEYWORD
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.resolve.BindingContext

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
    ): Modality? =
            if (currentModality != FINAL) {
                null
            } else if (!isImplicitModality && modifierListOwner.hasModifier(FINAL_KEYWORD)) {
                FINAL // Explicit final
            } else {
                OPEN
            }
}

@AutoService(CommandLineProcessor::class)
class ReallyAllOpenCommandLineProcessor : CommandLineProcessor {

    override val pluginId: String = "no.synth.kotlin.plugins.kotlin-really-allopen"

    override val pluginOptions: Collection<CliOption> = listOf(
            CliOption(
                    optionName = "enabled", valueDescription = "<true|false>",
                    description = "whether to enable the plugin or not"
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