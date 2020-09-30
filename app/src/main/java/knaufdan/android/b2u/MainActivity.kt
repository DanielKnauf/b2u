package knaufdan.android.b2u

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.mvvm.implementation.AndroidBaseViewModel
import knaufdan.android.arch.mvvm.implementation.BaseActivity
import knaufdan.android.arch.mvvm.implementation.BaseFragment
import knaufdan.android.arch.navigation.ContainerViewId
import knaufdan.android.core.resources.IResourceProvider

class MainActivity : BaseActivity<MainActivityViewModel>() {
    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getLayoutRes(): LayoutRes = R.layout.activity_main

    override fun getFragmentSetup(): Pair<ContainerViewId, BaseFragment<out AndroidBaseViewModel>?>? =
        R.id.fragment_container to MainFragment()

    override fun getActivityTitleRes(): Int = IResourceProvider.EMPTY_STRING_ID
}
