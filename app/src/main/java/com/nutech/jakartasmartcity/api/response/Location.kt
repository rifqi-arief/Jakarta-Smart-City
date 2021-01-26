package com.nutech.jakartasmartcity.api.response

data class Location (
    val street : Street,
    val city : String,
    val state : String,
    val country : String,
    val postcode : Int,
    val coordinates : Coordinate,
    val timezone : Timezone
)