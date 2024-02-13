package com.test.yamoowikiproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name = "userId") var userId: String,
    @ColumnInfo(name = "userNickName") var userNickName: String,
    @ColumnInfo(name = "userPw") var userPw: String,
    @ColumnInfo(name = "userPhone") var userPhone: String,
    @ColumnInfo(name = "userEmail") var userEmail: String
)
