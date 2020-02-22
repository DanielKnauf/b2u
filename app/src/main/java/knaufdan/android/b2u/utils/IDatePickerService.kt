package knaufdan.android.b2u.utils

typealias DayOfMonth = Int
typealias Month = Int
typealias Year = Int

interface IDatePickerService {

    fun showDatePicker(
        date: Triple<DayOfMonth, Month, Year>,
        minDate: Number?,
        onCancelClicked: () -> Unit = {},
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )

    fun showDatePicker(
        dayOfMonth: DayOfMonth,
        month: Month,
        year: Year,
        minDate: Number?,
        onCancelClicked: () -> Unit = {},
        onDatePicked: (DayOfMonth, Month, Year) -> Unit
    )
}
