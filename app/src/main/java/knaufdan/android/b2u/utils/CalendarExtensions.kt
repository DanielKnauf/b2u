package knaufdan.android.b2u.utils

import java.util.Calendar

private val today: Calendar
    get() = Calendar.getInstance()

fun getTodayDayOfMonth() = today.getDayOfMonth()

fun Calendar.getDayOfMonth() = get(Calendar.DAY_OF_MONTH)

fun getTodayMonth() = today.getMonth()

fun Calendar.getMonth() = get(Calendar.MONTH)

fun getTodayYear() = today.getYear()

fun Calendar.getYear() = get(Calendar.YEAR)
