package knaufdan.android.b2u

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.mvvm.implementation.BaseActivity
import knaufdan.android.arch.mvvm.implementation.BaseFragment
import knaufdan.android.arch.mvvm.implementation.BaseViewModel
import knaufdan.android.arch.navigation.ContainerViewId

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getLayoutRes(): LayoutRes = R.layout.activity_main

    override fun getFragmentSetup(): Pair<ContainerViewId, BaseFragment<out BaseViewModel>?>? =
        R.id.fragment_container to MainFragment()
}
