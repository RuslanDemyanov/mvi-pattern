package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun action(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi

import com.emlid.reachview3.mvibase.MviAction

sealed class ${className}Action : MviAction {

    object Initial : ${className}Action()

    object Exit : ${className}Action()
}
"""
