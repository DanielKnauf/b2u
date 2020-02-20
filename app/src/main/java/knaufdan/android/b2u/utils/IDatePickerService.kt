package knaufdan.android.b2u.utils

interface IDatePickerService {

    fun showDatePicker(
        date: Triple<DayOfMonth, Month, Year>,
        minDate: Long,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )

    fun showDatePicker(
        dayOfMonth: DayOfMonth,
        month: Month,
        year: Year,
        minDate: Long,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )
}
