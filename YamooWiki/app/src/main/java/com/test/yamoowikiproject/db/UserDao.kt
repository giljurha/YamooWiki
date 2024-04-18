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

    @Query("SELECT * FROM UserEntity WHERE userId = :userId AND userPassword = :userPassword LIMIT 1")
    fun getUserByIdPassword(userId: String, userPassword: String): UserEntity?

    @Query("SELECT userId FROM UserEntity WHERE userId = :userId")
    fun getUserId(userId: String): String?

    @Query("SELECT userNickName FROM UserEntity WHERE userNickName = :userNickName")
    fun getUserNickName(userNickName: String): String?


}
