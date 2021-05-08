package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun mutation(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi

import com.emlid.reachview3.mvibase.MviMutation

sealed class ${className}Mutation : MviMutation<${className}State> {

    object Idle : ${className}Mutation() {

        override fun mutate(previousState: ${className}State): ${className}State = 
            previousState
    }
}
"""
