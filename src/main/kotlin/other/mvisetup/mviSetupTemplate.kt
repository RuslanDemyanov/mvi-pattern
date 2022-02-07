package other.mvisetup

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template

val mviSetupTemplate
    get() = template {
        name = "MVI Full Template"
        description = "Creates a new MVI component structure."
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val className = stringParameter {
            name = "Class Name"
            default = "Test"
            help = "The name of the class to create and use in MVI and Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val packageName = stringParameter {
            name = "Package name"
            default = "com.emlid.reachview3.ui"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { packageName }
        }

        val containerName = stringParameter {
            name = "Container name"
            default = "SurveyContainer"
            help = "The name of the container that will be used in router side effect"
            constraints = listOf(Constraint.NONEMPTY)
        }

        widgets(
            TextFieldWidget(className),
            TextFieldWidget(packageName),
            TextFieldWidget(containerName)
        )

        recipe = { data: TemplateData ->
            mviSetup(
                data as ModuleTemplateData,
                packageName.value,
                className.value,
                containerName.value
            )
        }
    }

private const val MIN_API = 16
