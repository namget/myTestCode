package com.namget.testcode.data.source.local.dao

import androidx.room.Dao
import com.namget.testcode.data.source.local.entity.MyDto

/**
 * Created by Namget on 2019.10.17.
 */
@Dao
interface MyDao : BaseDao<MyDto> {

//    @Query("SELECT * FROM myDto WHERE id = :id")
//    fun selectById(id: Int)
//
//    @Query("SELECT * FROM myDto")
//    fun selectAll()
//
//    @Query("SELECT * FROM myDto WHERE date = :date")
//    fun selectCountByDate(date: String)
//
//    @Query("SELECT * FROM myDto WHERE date = :date")
//    fun selectByDate(date: String)
//
//    @Query("DELETE FROM myDto WHERE date = :date")
//    fun deleteByDate(date: String)


}