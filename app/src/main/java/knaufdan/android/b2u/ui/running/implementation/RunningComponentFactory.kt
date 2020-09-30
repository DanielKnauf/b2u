package knaufdan.android.b2u.ui.running.implementation

import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.ui.flickeringpoints.IFlickeringPointsComponentFactory
import knaufdan.android.b2u.ui.running.IRunningComponent
import knaufdan.android.b2u.ui.running.IRunningComponentFactory

class RunningComponentFactory(
    private val textFactory: ITextComponentFactory,
    private val flickeringPointsComponentFactory: IFlickeringPointsComponentFactory
) : IRunningComponentFactory {
    override fun create(
        endDate: Triple<DayOfMonth, Month, Year>,
        onFinished: () -> Unit
    ): IRunningComponent =
        RunningComponent(
            textFactory,
            flickeringPointsComponentFactory,
            endDate,
            onFinished
        )
}
