package knaufdan.android.b2u.ui.running.implementation

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.BR
import knaufdan.android.b2u.R
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponentFactory
import knaufdan.android.b2u.ui.running.IRunningComponent

class RunningComponent(
    factory: ITextComponentFactory,
    flickeringPointsComponentFactory: IFlickeringPointsComponentFactory,
    endDate: Triple<DayOfMonth, Month, Year>,
    onFinished: () -> Unit
) : IRunningComponent {
    private val viewModel: RunningViewModel by lazy {
        RunningViewModel(
            factory,
            flickeringPointsComponentFactory,
            endDate,
            onFinished
        )
    }

    override fun getLayoutRes(): LayoutRes = R.layout.running

    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getDataSource(): RunningViewModel = viewModel
}
