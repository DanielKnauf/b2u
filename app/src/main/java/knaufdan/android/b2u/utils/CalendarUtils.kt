package knaufdan.android.b2u.utils

import java.util.Calendar

object CalendarUtils {
    private val rightNow: Calendar
        get() = Calendar.getInstance()

    fun getTodayDayOfMonth(): DayOfMonth = rightNow.getDayOfMonth()

    fun getTodayMonth(): Month = rightNow.getMonth()

    fun getTodayYear(): Year = rightNow.getYear()

    fun getToday(): Triple<DayOfMonth, Month, Year> = Triple(
        first = getTodayDayOfMonth(),
        second = getTodayMonth(),
        third = getTodayYear()
    )
}

fun Calendar.getDayOfMonth(): DayOfMonth = get(Calendar.DAY_OF_MONTH)

fun Calendar.getMonth(): Month = get(Calendar.MONTH)

fun Calendar.getYear(): Year = get(Calendar.YEAR)
