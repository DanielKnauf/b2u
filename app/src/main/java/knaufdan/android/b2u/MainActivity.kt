package knaufdan.android.b2u

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.mvvm.implementation.BaseActivity

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getLayoutRes(): LayoutRes = R.layout.activity_main
}
