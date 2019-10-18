package com.namget.testcode.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.namget.testcode.data.source.local.dao.mDao
import com.namget.testcode.data.source.local.entity.mEntity

/**
 * Created by Namget on 2019.10.17.
 */

@Database(entities = [mEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mDao(): mDao

    companion object {

        fun buildDataBase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "myDbFile.db"
        ).build()

    }
}