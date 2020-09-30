package knaufdan.android.b2u.ui.flickeringpoints.implementation

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.b2u.BR
import knaufdan.android.b2u.R
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponent

class FlickeringPointsComponent() : IFlickeringPointsComponent {
    private val viewModel: FlickeringPointsViewModel by lazy {
        FlickeringPointsViewModel()
    }

    override fun getLayoutRes(): LayoutRes = R.layout.flickering_points

    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getDataSource(): FlickeringPointsViewModel = viewModel
}
