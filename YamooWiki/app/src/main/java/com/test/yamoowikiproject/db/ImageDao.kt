package com.test.yamoowikiproject.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ImageDao {
    @Insert
    fun insert(imageEntity: ImageEntity)

    @Delete
    fun delete(imageEntity: ImageEntity)



}