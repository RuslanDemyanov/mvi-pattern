package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun intent(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi

import com.emlid.reachview3.mvibase.MviIntent

sealed class ${className}Intent : MviIntent {

    object Initial : ${className}Intent()

    object Exit : ${className}Intent()
}
"""
