package com.nutech.jakartasmartcity.db

import android.content.Context
import androidx.room.*
import com.nutech.jakartasmartcity.api.response.Contacts

@Database(
    entities = [Contacts::class],
    version = 1
)

@TypeConverters(Converters::class)

abstract class ContactDatabase : RoomDatabase() {

    abstract fun    getContactDao():ContactDao

    companion object{
        @Volatile
        private var instance : ContactDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ContactDatabase::class.java,
                "contact_db.db"
            ).build()
    }
}