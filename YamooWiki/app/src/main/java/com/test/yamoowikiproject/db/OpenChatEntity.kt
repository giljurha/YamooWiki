package com.test.yamoowikiproject.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class OpenChatEntity(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    @ColumnInfo(name = "openChatName") val openChatName: String,
    @ColumnInfo(name = "openChatOpener") val openChatOpener: String,
    @ColumnInfo(name = "oepnChatStartDay") val openChatStartDay: String,
)
