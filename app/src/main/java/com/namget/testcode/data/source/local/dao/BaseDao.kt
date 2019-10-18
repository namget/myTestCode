package com.namget.testcode.data.source.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Created by Namget on 2019.10.17.
 */
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj : T)

    @Delete
    fun delete(obj : T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj : T)
}
