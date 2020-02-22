package knaufdan.android.b2u.utils

interface IDatePickerService {

    fun showDatePicker(
        date: Triple<DayOfMonth, Month, Year>,
        minDate: Number?,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )

    fun showDatePicker(
        dayOfMonth: DayOfMonth,
        month: Month,
        year: Year,
        minDate: Number?,
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )
}
