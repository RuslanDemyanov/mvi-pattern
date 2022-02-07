package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun screen(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Intent
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}ViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ${className}Screen() {
    val viewModel = getViewModel<${className}ViewModel>()

    val uiState by viewModel.subscribeAsState(${className}Intent.Initial)

    BackHandler {
        viewModel.sendIntent(${className}Intent.Exit)
    }
}
"""
