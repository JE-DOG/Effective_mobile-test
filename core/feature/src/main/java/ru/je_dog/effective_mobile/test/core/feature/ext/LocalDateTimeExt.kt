package ru.je_dog.effective_mobile.test.core.feature.ext

import android.util.Log
import java.time.LocalDateTime
import kotlin.math.min

val LocalDateTime.simpleHourAndMinutes: String get() {
    val timeText = StringBuilder()
    if (hour < 10){
        timeText.append('0')
    }
    timeText.append(hour)
    timeText.append(':')
    if (minute < 10){
        timeText.append('0')
    }
    timeText.append(minute)

    return timeText.toString()
}

fun LocalDateTime.getHourDiff(diffTime: LocalDateTime): Float {
    var diffHours = if (hour < diffTime.hour){
        hour.toFloat() + 24 - diffTime.hour
    }else {
        hour.toFloat() - diffTime.hour
    }
    var diffDays = dayOfYear - diffTime.dayOfYear
    while (diffDays > 1){
        diffDays--
        diffHours += 24
    }
    var diffMinutes = if (diffHours > 0){

        minute + 60 - diffTime.minute
    }else {
        minute - diffTime.minute
    }
    if (diffMinutes > 60){
        diffMinutes -= 60
        diffHours++
    }
    if (diffMinutes <= 30){
        diffHours += 0.5f
    }else {
        diffHours++
    }
    return diffHours
}