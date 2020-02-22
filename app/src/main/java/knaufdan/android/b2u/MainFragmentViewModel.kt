package knaufdan.android.b2u

import android.widget.Toast
import javax.inject.Inject
import knaufdan.android.arch.mvvm.implementation.BaseViewModel
import knaufdan.android.b2u.utils.CalendarUtils
import knaufdan.android.b2u.utils.IDatePickerService
import knaufdan.android.core.IContextProvider

class MainFragmentViewModel @Inject constructor(
    contextProvider: IContextProvider,
    datePickerService: IDatePickerService
) : BaseViewModel() {

    init {
        datePickerService.showDatePicker(
            dayOfMonth = CalendarUtils.getTodayDayOfMonth(),
            month = CalendarUtils.getTodayMonth(),
            year = CalendarUtils.getTodayYear(),
            minDate = System.currentTimeMillis()
        ) { d, m, y ->
            Toast.makeText(contextProvider.getContext(), "$d | $m | $y", Toast.LENGTH_LONG).show()
        }
    }
}
