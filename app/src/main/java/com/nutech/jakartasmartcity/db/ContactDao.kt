package com.nutech.jakartasmartcity.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nutech.jakartasmartcity.api.response.Contacts

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(contacts: Contacts): Long

    @Query("SELECT * FROM contact")
    fun getAllContact(): LiveData<List<Contacts>>

    @Delete
    suspend fun deleteContact(contact: Contacts)
}