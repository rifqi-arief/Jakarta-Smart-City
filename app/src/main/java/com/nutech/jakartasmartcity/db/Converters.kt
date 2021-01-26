package com.nutech.jakartasmartcity.db

import androidx.room.TypeConverter
import com.nutech.jakartasmartcity.api.response.Name

class Converters {
    @TypeConverter
    fun fromName(name: Name) : String {
        return name.first
    }

    @TypeConverter
    fun toName(name: String) : Name {
        return Name(name,name,name)
    }

}