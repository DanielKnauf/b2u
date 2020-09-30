package knaufdan.android.b2u.ui.input.implementation

import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.IDatePickerService
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.ui.input.IInputComponent
import knaufdan.android.b2u.ui.input.IInputComponentFactory
import knaufdan.android.core.preferences.ISharedPrefService

class InputComponentFactory(
    private val datePickerService: IDatePickerService,
    private val sharedPrefService: ISharedPrefService,
    private val factory: ITextComponentFactory
) : IInputComponentFactory {
    override fun create(onDatePicked: (date: DayOfMonth, Month, Year) -> Unit): IInputComponent =
        InputComponent(
            datePickerService = datePickerService,
            sharedPrefService = sharedPrefService,
            factory = factory,
            onDatePicked = onDatePicked
        )
}
