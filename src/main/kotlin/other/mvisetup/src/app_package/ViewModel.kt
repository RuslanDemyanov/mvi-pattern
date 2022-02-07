package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun viewModel(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi

import com.emlid.reachview3.mvibase.MviViewModel
import $packageName.mvi.sideeffects.${className}RouterSideEffect
import $packageName.mvi.sideeffects.${className}SideEffect

class ${className}ViewModel(private val routerSideEffect: ${className}RouterSideEffect) : MviViewModel<
    ${className}Intent,
    ${className}Action,
    ${className}State,
    ${className}Mutation,
    ${className}SideEffect>(
    ${className}State(), ${className}Intent.Initial
) {

    override fun intentToAction(intent: ${className}Intent): ${className}Action =
        when (intent) {
            is ${className}Intent.Initial -> ${className}Action.Initial
            is ${className}Intent.Exit -> ${className}Action.Exit
        }

    override fun actionToMutation(action: ${className}Action): ${className}Mutation =
        when (action) {
            is ${className}Action.Initial,
            is ${className}Action.Exit -> ${className}Mutation.Idle
        }

    override fun sideEffects(): List<${className}SideEffect> =
        listOf(
            routerSideEffect
        )
}
"""
