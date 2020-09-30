package knaufdan.android.b2u.datepicker

import java.util.Calendar
import java.util.concurrent.TimeUnit

val today: Triple<DayOfMonth, Month, Year>
    get() = Triple(
        first = getTodayDayOfMonth(),
        second = getTodayMonth(),
        third = getTodayYear()
    )

private val rightNow: Calendar
    get() = Calendar.getInstance()

fun getTodayDayOfMonth(): DayOfMonth = rightNow.getDayOfMonth()

fun getTodayMonth(): Month = rightNow.getMonth()

fun getTodayYear(): Year = rightNow.getYear()

fun Calendar.getDayOfMonth(): DayOfMonth = get(Calendar.DAY_OF_MONTH)

fun Calendar.getMonth(): Month = get(Calendar.MONTH)

fun Calendar.getYear(): Year = get(Calendar.YEAR)

fun Triple<DayOfMonth, Month, Year>.daysBetween(other: Triple<DayOfMonth, Month, Year>): Int {
    if ((other.third == this.third && other.second < this.second) ||
        (other.third < this.third) ||
        (other.third == this.third && other.second == this.second && other.first < this.first)
    ) {
        return 0
    }

    val cal = Calendar.getInstance().apply {
        set(third, second, first)
    }
    val otherCal = Calendar.getInstance().apply {
        set(other.third, other.second, other.first)
    }

    val timeBetween = otherCal.timeInMillis - cal.timeInMillis
    return TimeUnit.MILLISECONDS.toDays(timeBetween).toInt()
}
