package com.nunnos.warofsuitsjoseppuit.utils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {
    companion object{

        fun dateToString(calendar: Calendar): String? {
            return dateToString(calendar, null)
        }

        fun dateToString(calendar: Calendar, dateFormat: String?): String? {
            return try {
                val format1 = SimpleDateFormat(dateFormat)
                format1.format(calendar.time)
            } catch (e: Exception) {
                val format1 = SimpleDateFormat("dd/MM/yyyy")
                format1.format(calendar.time)
            }
        }
        fun timeToString(calendar: Calendar): String? {
            return timeToString(calendar, null)
        }

        fun timeToString(calendar: Calendar, dateFormat: String?): String? {
            return try {
                val format1 = SimpleDateFormat(dateFormat)
                format1.format(calendar.time)
            } catch (e: Exception) {
                val format1 = SimpleDateFormat("HH:mm")
                format1.format(calendar.time)
            }
        }
    }
}