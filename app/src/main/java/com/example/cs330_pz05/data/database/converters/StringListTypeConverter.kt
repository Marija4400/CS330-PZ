package com.example.cs330_pz05.data.database.converters

import androidx.room.TypeConverter

class StringListTypeConverter {

    @TypeConverter
    fun fromStringList(stringList: List<String>): String {
        return stringList.joinToString(",")
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.split(",")
    }
}