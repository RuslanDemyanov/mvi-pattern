package other.mvisetup

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.booleanParameter
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

        val packageNameParam = defaultPackageNameParameter
        val className = stringParameter {
            name = "Class Name"
            default = "Test"
            help = "The name of the class to create and use in MVI and Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }
        val generateLayout = booleanParameter {
            name = "Generate a Layout File"
            default = true
            help = "If true, a layout file will be generated"
        }

        widgets(
            TextFieldWidget(className),
            PackageNameWidget(packageNameParam),
            CheckBoxWidget(generateLayout)
        )

        recipe = { data: TemplateData ->
            mviSetup(
                data as ModuleTemplateData,
                packageNameParam.value,
                className.value,
                generateLayout.value
            )
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.emlid.reachview3.ui"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }

const val MIN_API = 16
