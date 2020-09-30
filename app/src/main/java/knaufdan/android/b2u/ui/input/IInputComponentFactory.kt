package knaufdan.android.b2u.ui.input

import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year

interface IInputComponentFactory {
    fun create(
        onDatePicked: (date: DayOfMonth, Month, Year) -> Unit
    ): IInputComponent
}
