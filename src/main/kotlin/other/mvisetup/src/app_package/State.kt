package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun state(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi

import com.emlid.reachview3.mvibase.MviState

class ${className}State : MviState
"""
