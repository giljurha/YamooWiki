package com.test.yamoowikiproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OpenPostEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "openPostName") val openPostName: String,
    @ColumnInfo(name = "openPostStartDay") val openPostStartDay: String
)
