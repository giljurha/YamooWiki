package com.test.yamoowikiproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun insertUser(userEntity: UserEntity)

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE userId = :userId AND userPassword = :password LIMIT 1")
    fun getUserInfo(userId: String, password: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE userId = :userId")
    fun getUserId(userId: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE userNickname = :userNickName")
    fun getUserNickname(userNickName: String): UserEntity?


}
