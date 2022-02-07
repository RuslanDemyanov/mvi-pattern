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

fun routerSideEffect(
    packageName: String,
    className: String,
    containerName: String
) = """package ${escapeKotlinIdentifier(packageName)}.mvi.sideeffects

import com.emlid.reachview3.navigation.AppNavigation
import com.emlid.reachview3.navigation.containers.${containerName}
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Action
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}State
import io.reactivex.Observable

class ${className}RouterSideEffect(private val appNavigation: AppNavigation) : ${className}SideEffect {

    override fun execute(
        previousState: ${className}State,
        currentState: ${className}State,
        action: ${className}Action
    ): Observable<${className}Action>? {
        when (action) {
            is ${className}Action.Exit -> appNavigation.exit(${containerName}.TAG)
            else -> Unit
        }

        return null
    }
}
"""