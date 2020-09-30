package knaufdan.android.b2u.ui.splash.implementation

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.BR
import knaufdan.android.b2u.R
import knaufdan.android.b2u.ui.splash.ISplashComponent

class SplashComponent(
    factory: ITextComponentFactory,
    onFinish: () -> Unit
) : ISplashComponent {
    private val viewModel: SplashViewModel by lazy {
        SplashViewModel(
            factory = factory,
            onFinish = onFinish
        )
    }

    override fun getLayoutRes(): LayoutRes = R.layout.splash

    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getDataSource(): SplashViewModel = viewModel
}
