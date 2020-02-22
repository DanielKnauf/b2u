package knaufdan.android.b2u

import android.widget.Toast
import javax.inject.Inject
import knaufdan.android.arch.mvvm.implementation.BaseViewModel
import knaufdan.android.b2u.utils.CalendarUtils
import knaufdan.android.b2u.utils.DayOfMonth
import knaufdan.android.b2u.utils.IDatePickerService
import knaufdan.android.b2u.utils.Year
import knaufdan.android.core.IContextProvider

class MainFragmentViewModel @Inject constructor(
    private val contextProvider: IContextProvider,
    private val datePickerService: IDatePickerService
) : BaseViewModel() {

    fun onButtonClicked() {
        datePickerService.showDatePicker(
            dayOfMonth = CalendarUtils.getTodayDayOfMonth(),
            month = CalendarUtils.getTodayMonth(),
            year = CalendarUtils.getTodayYear(),
            minDate = System.currentTimeMillis(),
            onCancelClicked = this::onCancelClicked,
            onDatePicked = this::onDatePicked
        )
    }

    private fun onCancelClicked() {
        Toast.makeText(
            contextProvider.getContext(),
            "Cancel",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onDatePicked(
        dayOfMonth: DayOfMonth,
        month: DayOfMonth,
        year: Year
    ) {
        Toast.makeText(
            contextProvider.getContext(),
            "$dayOfMonth | ${month + 1} | $year",
            Toast.LENGTH_SHORT
        ).show()
    }
}
