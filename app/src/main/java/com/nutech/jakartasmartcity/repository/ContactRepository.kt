package com.nutech.jakartasmartcity.repository

import com.nutech.jakartasmartcity.api.RetrofitInstance
import com.nutech.jakartasmartcity.api.response.Contacts
import com.nutech.jakartasmartcity.db.ContactDatabase

class ContactRepository (val db : ContactDatabase ){
    suspend fun getContacts() = RetrofitInstance.api.getContact()

    suspend fun upsert(contacts: Contacts) = db.getContactDao().upsert(contacts)

    fun getSavedContact() = db.getContactDao().getAllContact()

    suspend fun deleteContact(contacts: Contacts) = db.getContactDao().deleteContact(contacts)
}