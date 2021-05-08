package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun sideEffect(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi.sideeffects

import com.emlid.reachview3.mvibase.MviSideEffect
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Action
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}State

typealias ${className}SideEffect = MviSideEffect<${className}Action, ${className}State>
"""
