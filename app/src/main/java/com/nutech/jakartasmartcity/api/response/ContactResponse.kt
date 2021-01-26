package com.nutech.jakartasmartcity.api.response

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    val results: MutableList<Contacts>
)