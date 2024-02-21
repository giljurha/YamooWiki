package com.test.yamoowikiproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface OpenChatDao {
    @Insert
    fun insertOpenChat(openChat : OpenChatEntity)

    @Delete
    fun deleteOpenChat(openChat : OpenChatEntity)

    @Query("SELECT * FROM OpenChatEntity")
    fun getAllTodo() : List<OpenChatEntity>
}

