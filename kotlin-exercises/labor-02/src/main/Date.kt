package main

import java.time.LocalDate

data class Date(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth
) : Comparable<Date> {

    override fun compareTo(other: Date): Int {
        return when {
            year != other.year -> {
                year - other.year
            }
            month != other.month -> {
                month - other.month
            }
            else -> {
                day - other.day
            }
        }
    }

    fun leapYear(): Boolean {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0
    }

    fun checkValidDate(): Boolean {

        val maxDayOfMonth = LocalDate.of(year, month, 1).lengthOfMonth()

        return (year in 1 .. LocalDate.now().year)
                && (month in 1..12)
                && (day in 1 .. maxDayOfMonth)
    }

}

