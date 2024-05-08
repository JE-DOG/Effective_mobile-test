package ru.je_dog.effective_mobile.test.core.data.room_utiil

import androidx.room.TypeConverter

class StringListConvertor {
    @TypeConverter
    fun fromStringList(items: List<String?>): String {
        return items.joinToString(",")
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        return data.split(',')
    }
}