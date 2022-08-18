package other.mvisetup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.github.cqrlm.mvipattern.listeners.MyProjectManagerListener.Companion.projectInstance
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.KotlinLanguage
import other.mvisetup.src.app_package.action
import other.mvisetup.src.app_package.fragment
import other.mvisetup.src.app_package.intent
import other.mvisetup.src.app_package.mutation
import other.mvisetup.src.app_package.mutationTests
import other.mvisetup.src.app_package.routerSideEffect
import other.mvisetup.src.app_package.screen
import other.mvisetup.src.app_package.sideEffect
import other.mvisetup.src.app_package.state
import other.mvisetup.src.app_package.stateTests
import other.mvisetup.src.app_package.viewModel
import other.mvisetup.src.app_package.viewModelTests

fun RecipeExecutor.mviSetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    containerName: String,
) {
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val srcVirtualFile = virtualFiles.first { it.path.contains("ReachView/app/src/main/(java|kotlin)".toRegex()) }
    val testVirtualFile = virtualFiles.first { it.path.contains("ReachView/app/src/test/(java|kotlin)".toRegex()) }
    val srcDir = PsiManager.getInstance(project).findDirectory(srcVirtualFile)!!
    val testDir = PsiManager.getInstance(project).findDirectory(testVirtualFile)!!

    fragment(packageName, className)
        .save(srcDir, packageName, "${className}Fragment.kt")

    screen(packageName, className)
        .save(srcDir, packageName, "${className}Screen.kt")

    generateMvi(srcDir, packageName, className, containerName)

    generateMviTests(testDir, packageName, className)
}

fun generateMvi(srcDir: PsiDirectory, packageName: String, className: String, containerName: String) {
    action(packageName, className)
        .save(srcDir, "$packageName.mvi", "${className}Action.kt")

    intent(packageName, className)
        .save(srcDir, "$packageName.mvi", "${className}Intent.kt")

    mutation(packageName, className)
        .save(srcDir, "$packageName.mvi", "${className}Mutation.kt")

    state(packageName, className)
        .save(srcDir, "$packageName.mvi", "${className}State.kt")

    viewModel(packageName, className)
        .save(srcDir, "$packageName.mvi", "${className}ViewModel.kt")

    sideEffect(packageName, className)
        .save(srcDir, "$packageName.mvi.sideeffects", "${className}SideEffect.kt")

    routerSideEffect(packageName, className, containerName)
        .save(srcDir, "$packageName.mvi.sideeffects", "${className}RouterSideEffect.kt")
}

fun generateMviTests(testDir: PsiDirectory, packageName: String, className: String) {
    stateTests(packageName, className)
        .save(testDir, packageName, "${className}StateTests.kt")

    mutationTests(packageName, className)
        .save(testDir, packageName, "${className}MutationsTests.kt")

    viewModelTests(packageName, className)
        .save(testDir, packageName, "${className}ViewModelTests.kt")
}

fun String.save(srcDir: PsiDirectory, subDirPath: String, fileName: String) {
    val destDir = subDirPath.split(".").toDir(srcDir)
    val psiFile = PsiFileFactory
        .getInstance(srcDir.project)
        .createFileFromText(fileName, KotlinLanguage.INSTANCE, this)
    destDir.add(psiFile)
}

fun List<String>.toDir(srcDir: PsiDirectory): PsiDirectory {
    var result = srcDir
    forEach {
        result = result.findSubdirectory(it) ?: result.createSubdirectory(it)
    }
    return result
}
