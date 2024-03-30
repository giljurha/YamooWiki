package com.test.yamoowikiproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OpenPostDao {
    @Insert
    fun insertOpenPost(openPost: OpenPostEntity)

    @Delete
    fun deleteOpenPost(openChat: OpenPostEntity)

    @Query("SELECT * FROM OpenPostEntity WHERE OpenPostName = :openPostName LIMIT 1")
    fun getOpenChat(openPostName: String): List<OpenPostEntity>
}