package com.namget.testcode.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Namget on 2019.10.17.
 * 이름변경필요
 */
@Entity(tableName = "mEntity")
data class mEntity(@PrimaryKey(autoGenerate = true) val id: Long, var title: String, var content: String, var date: String)

