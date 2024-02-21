package com.test.yamoowikiproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "userNickName") val userNickName: String,
    @ColumnInfo(name = "userPassword") val userPassword: String,
)
