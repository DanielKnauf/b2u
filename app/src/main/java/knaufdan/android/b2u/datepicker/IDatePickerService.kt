package knaufdan.android.b2u.datepicker

typealias DayOfMonth = Int
typealias Month = Int
typealias Year = Int

interface IDatePickerService {
    fun showDatePicker(
        date: Triple<DayOfMonth, Month, Year> = today,
        minDate: Number? = System.currentTimeMillis(),
        onCancelClicked: () -> Unit = {},
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )

    fun showDatePicker(
        dayOfMonth: DayOfMonth = getTodayDayOfMonth(),
        month: Month = getTodayMonth(),
        year: Year = getTodayYear(),
        minDate: Number? = System.currentTimeMillis(),
        onCancelClicked: () -> Unit = {},
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )
}
