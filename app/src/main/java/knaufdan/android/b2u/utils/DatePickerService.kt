package knaufdan.android.b2u.utils

import android.app.DatePickerDialog
import javax.inject.Inject
import knaufdan.android.core.IContextProvider

class DatePickerService @Inject constructor(
    private val contextProvider: IContextProvider
) : IDatePickerService {

    override fun showDatePicker(
        date: Triple<DayOfMonth, Month, Year>,
        minDate: Number?,
        onCancelClicked: () -> Unit,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    ) {
        showDatePicker(
            dayOfMonth = date.first,
            month = date.second,
            year = date.third,
            minDate = minDate,
            onCancelClicked = onCancelClicked,
            onDatePicked = onDatePicked
        )
    }

    override fun showDatePicker(
        dayOfMonth: DayOfMonth,
        month: Month,
        year: Year,
        minDate: Number?,
        onCancelClicked: () -> Unit,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    ) {
        check(dayOfMonth in 1..31) { "DayOfMonth must be between 1 and 31, currently dayOfMonth == $dayOfMonth" }
        check(month in 0..11) { "Month must be between 0 and 11, currently month == $month" }
        check(year >= 1900) { "Year must be greater than 1900, currently year == $year" }

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
            if (minDate != null) {
                datePicker.minDate = minDate.toLong()
            }

            setOnCancelListener {
                onCancelClicked()
            }
        }.show()
    }
}
