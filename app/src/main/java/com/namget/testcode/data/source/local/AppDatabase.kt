package com.namget.testcode.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namget.testcode.data.source.local.dao.MyDao
import com.namget.testcode.data.source.local.entity.Entity
import com.namget.testcode.data.source.local.entity.MyDto

/**
 * Created by Namget on 2019.10.17.
 */

@Database(entities = [Entity::class , MyDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mDao(): MyDao
}