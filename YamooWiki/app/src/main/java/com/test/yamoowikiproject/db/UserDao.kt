package com.test.yamoowikiproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(user : UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user : UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getAllTodo() : List<UserEntity>

}