package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.mvisetup.src.utils.separateCamelCase

fun stateTests(
    packageName: String,
    className: String,
) = """package ${escapeKotlinIdentifier(packageName)}

import ${escapeKotlinIdentifier(packageName)}.mvi.${className}State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("${className.separateCamelCase()} state")
class ${className}StateTests {

    @Test
    fun `check that initial state is correct`() {
        val state = ${className}State()

        assertThat(state).isInstanceOf(${className}State::class.java)
    }
}
"""

fun mutationTests(
    packageName: String,
    className: String,
) = """package ${escapeKotlinIdentifier(packageName)}
    
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Mutation
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("${className.separateCamelCase()} mutations")
class ${className}MutationsTests {

    private val state = ${className}State()

    @Test
    fun `check result of Idle mutation`() {
        val mutatedState = ${className}Mutation.Idle.mutate(state)

        assertThat(mutatedState).isEqualTo(state)
    }
}
"""

fun viewModelTests(
    packageName: String,
    className: String,
) = """package ${escapeKotlinIdentifier(packageName)}

import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Action
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Intent
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Mutation
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}ViewModel
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("${className.separateCamelCase()} view model tests")
class ${className}ViewModelTests {

    private val viewModel = ${className}ViewModel(
        routerSideEffect = mockk()
    )

    @Nested
    @DisplayName("intentToAction method")
    inner class IntentToAction {

        @Test
        fun `Initial intent`() {
            assertThat(viewModel.intentToAction(${className}Intent.Initial))
                .isInstanceOf(${className}Action.Initial::class.java)
        }

        @Test
        fun `Exit intent`() {
            assertThat(viewModel.intentToAction(${className}Intent.Exit))
                .isInstanceOf(${className}Action.Exit::class.java)
        }
    }

    @Nested
    @DisplayName("actionToMutation method")
    inner class ActionToMutation {

        @Test
        fun `Initial action`() {
            assertThat(viewModel.actionToMutation(${className}Action.Initial))
                .isInstanceOf(${className}Mutation.Idle::class.java)
        }

        @Test
        fun `Exit action`() {
            assertThat(viewModel.actionToMutation(${className}Action.Exit))
                .isInstanceOf(${className}Mutation.Idle::class.java)
        }
    }
}
"""