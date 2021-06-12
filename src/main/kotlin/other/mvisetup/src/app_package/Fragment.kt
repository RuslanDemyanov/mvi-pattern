package other.mvisetup.src.app_package

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import other.mvisetup.camelToSnakeCase

fun fragment(
    packageName: String,
    className: String
) = """package ${escapeKotlinIdentifier(packageName)}

import android.os.Bundle
import android.view.View
import com.emlid.reachview3.R
import com.emlid.reachview3.databinding.Fragment${className}Binding
import com.emlid.reachview3.mvibase.MviView
import com.emlid.reachview3.navigation.BackButtonListener
import com.emlid.reachview3.ui.custom.viewbinding.viewBinding
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}Intent
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}State
import ${escapeKotlinIdentifier(packageName)}.mvi.${className}ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.merge
import io.reactivex.subjects.PublishSubject
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ${className}Fragment :
    ScopeFragment(R.layout.fragment_${className.camelToSnakeCase()}),
    MviView<${className}Intent, ${className}State>,
    BackButtonListener {

    private val viewModel: ${className}ViewModel by viewModel()
    private val viewBinding by viewBinding(Fragment${className}Binding::bind)

    private val compositeDisposable = CompositeDisposable()
    private val exitSubject: PublishSubject<${className}Intent.Exit> = PublishSubject.create()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        bind()
    }

    override fun bind() {
        compositeDisposable.add(viewModel.states().subscribe(::render))
        viewModel.subscribeToIntents(intents())
    }

    override fun render(state: ${className}State) {
    }

    override fun intents(): Observable<${className}Intent> =
        listOf(
            initialIntent(),
            exitIntent()
        ).merge()

    private fun initialIntent(): Observable<${className}Intent> =
        Observable.just(${className}Intent.Initial)

    private fun exitIntent(): Observable<${className}Intent.Exit> =
        exitSubject

    override fun onStop() {
        compositeDisposable.clear()
        viewModel.unsubscribeFromIntents()
        super.onStop()
    }

    override fun onBackPressed(): Boolean {
        exitSubject.onNext(${className}Intent.Exit)
        return true
    }

    companion object {

        fun newInstance(): ${className}Fragment =
            ${className}Fragment()
    }
}
"""
