package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun fragment(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.emlid.reachview3.navigation.ComposeFragment
import com.emlid.reachview3.navigation.ModalFragment
import com.emlid.reachview3.ui.theme.ReachViewTheme

class ${className}Fragment :
    Fragment(),
    ModalFragment,
    ComposeFragment {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(inflater.context).apply {
            setContent {
                ReachViewTheme {
                    ${className}Screen()
                }
            }
        }

    companion object {

        fun newInstance(): ${className}Fragment =
            ${className}Fragment()
    }
}
"""
