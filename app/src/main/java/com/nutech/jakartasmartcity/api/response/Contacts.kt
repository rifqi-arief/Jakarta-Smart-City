package com.nutech.jakartasmartcity.api.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "contact"
)

data class Contacts(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    val gender: String?,
    val name: Name?,
    val location: Location?,
    val email: String?,
    val dob: Dob?,
    val phone: String?,
    val cell: String?,
    val picture: Picture?
) : Serializable