package no.synth.kotlin.plugins.reallyallopen

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.compiler.plugin.ComponentRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
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

        return registerExtension(project, ReallyAllOpenExtension())
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

        return if (currentModality != FINAL) {
            null
        } else if (!isImplicitModality && modifierListOwner.hasModifier(FINAL_KEYWORD)) {
            FINAL // Explicit final
        } else {
            OPEN
        }
    }
}