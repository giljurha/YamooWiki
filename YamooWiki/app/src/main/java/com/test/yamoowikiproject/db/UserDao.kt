package com.test.yamoowikiproject.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE userId = :userId AND userPassword = :userPassword LIMIT 1")
    suspend fun getUserByIdPassword(userId: String, userPassword: String): UserEntity?

    @Query("SELECT * FROM UserEntity WHERE userId = :userId LIMIT 1")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT userId FROM UserEntity WHERE userId = :userId")
    suspend fun getUserId(userId: String): String?

    @Query("SELECT userNickName FROM UserEntity WHERE userNickName = :userNickName")
    suspend fun getUserNickName(userNickName: String): String?


}
