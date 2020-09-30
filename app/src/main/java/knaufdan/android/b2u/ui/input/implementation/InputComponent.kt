package knaufdan.android.b2u.ui.input.implementation

import knaufdan.android.arch.base.component.BindingKey
import knaufdan.android.arch.base.component.LayoutRes
import knaufdan.android.arch.base.component.text.ITextComponentFactory
import knaufdan.android.b2u.BR
import knaufdan.android.b2u.R
import knaufdan.android.b2u.datepicker.DayOfMonth
import knaufdan.android.b2u.datepicker.IDatePickerService
import knaufdan.android.b2u.datepicker.Month
import knaufdan.android.b2u.datepicker.Year
import knaufdan.android.b2u.ui.input.IInputComponent
import knaufdan.android.core.preferences.ISharedPrefService

class InputComponent(
    datePickerService: IDatePickerService,
    factory: ITextComponentFactory,
    sharedPrefService: ISharedPrefService,
    onDatePicked: (date: DayOfMonth, Month, Year) -> Unit
) : IInputComponent {
    val viewModel: InputViewModel by lazy {
        InputViewModel(
            datePickerService = datePickerService,
            factory = factory,
            sharedPrefs = sharedPrefService,
            onDatePicked = onDatePicked
        )
    }

    override fun getLayoutRes(): LayoutRes = R.layout.input

    override fun getBindingKey(): BindingKey = BR.viewModel

    override fun getDataSource(): InputViewModel = viewModel
}
