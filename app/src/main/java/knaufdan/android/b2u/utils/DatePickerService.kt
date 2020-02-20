package knaufdan.android.b2u.utils

import android.app.DatePickerDialog
import javax.inject.Inject
import knaufdan.android.core.IContextProvider

class DatePickerService @Inject constructor(
    private val contextProvider: IContextProvider
) : IDatePickerService {

    override fun showDatePicker(
        date: Triple<DayOfMonth, Month, Year>,
        minDate: Long,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    ) {
        showDatePicker(
            dayOfMonth = date.first,
            month = date.second,
            year = date.third,
            minDate = minDate,
            onDatePicked = onDatePicked
        )
    }

    override fun showDatePicker(
        dayOfMonth: DayOfMonth,
        month: Month,
        year: Year,
        minDate: Long,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    ) {
        val listener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
            onDatePicked(d, m, y)
        }

        DatePickerDialog(
            contextProvider.getContext(),
            listener,
            year,
            month,
            dayOfMonth
        ).apply {
            datePicker.minDate = minDate
        }.show()
    }
}
