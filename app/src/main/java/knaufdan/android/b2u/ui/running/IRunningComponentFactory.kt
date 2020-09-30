package knaufdan.android.b2u.ui.running

import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year

interface IRunningComponentFactory {
    fun create(
        endDate: Triple<DayOfMonth, Month, Year>,
        onFinished: () -> Unit
    ): IRunningComponent
}
