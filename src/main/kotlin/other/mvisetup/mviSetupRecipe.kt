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
import other.mvisetup.src.app_package.LAYOUT
import other.mvisetup.src.app_package.action
import other.mvisetup.src.app_package.fragment
import other.mvisetup.src.app_package.intent
import other.mvisetup.src.app_package.mutation
import other.mvisetup.src.app_package.sideEffect
import other.mvisetup.src.app_package.state
import other.mvisetup.src.app_package.viewModel

fun RecipeExecutor.mviSetup(
    moduleData: ModuleTemplateData,
    packageName: String,
    className: String,
    generateLayout: Boolean
) {
    val project = projectInstance ?: return

    addAllKotlinDependencies(moduleData)

    val virtualFiles = ProjectRootManager.getInstance(project).contentSourceRoots
    val virtSrc = virtualFiles.first { it.path.contains("main/(java|kotlin)".toRegex()) }
    val virtRes = virtualFiles.first { it.path.contains("main/res") }
    val directorySrc = PsiManager.getInstance(project).findDirectory(virtSrc)!!
    val directoryRes = PsiManager.getInstance(project).findDirectory(virtRes)!!

    fragment(packageName, className)
        .save(directorySrc, packageName, "${className}Fragment.kt")

    action(packageName, className)
        .save(directorySrc, "$packageName.mvi", "${className}Action.kt")

    intent(packageName, className)
        .save(directorySrc, "$packageName.mvi", "${className}Intent.kt")

    mutation(packageName, className)
        .save(directorySrc, "$packageName.mvi", "${className}Mutation.kt")

    state(packageName, className)
        .save(directorySrc, "$packageName.mvi", "${className}State.kt")

    viewModel(packageName, className)
        .save(directorySrc, "$packageName.mvi", "${className}ViewModel.kt")

    sideEffect(packageName, className)
        .save(directorySrc, "$packageName.mvi.sideeffects", "${className}SideEffect.kt")

    if (generateLayout) {
        LAYOUT
            .save(directoryRes, "layout", "fragment_${className.camelToSnakeCase()}.xml")
    }
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

fun String.camelToSnakeCase(): String {
    val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()

    return camelRegex.replace(this) {
        "_${it.value}"
    }.toLowerCase()
}
