package com.nutech.jakartasmartcity.api.services

import com.nutech.jakartasmartcity.api.response.ContactResponse
import com.nutech.jakartasmartcity.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RestApi {
    @GET("api?results=5&exc=login,registered,id,nat&nat=us&noinfo")
    suspend fun getContact(): Response<ContactResponse>
}