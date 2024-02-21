package com.test.yamoowikiproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserEntity::class, OpenChatEntity::class), version = 1)
abstract class YamooWikiDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao
    abstract fun getOpenChatDao() : OpenChatDao

    companion object {
        val databaseName = "yamooWikiDb"
        var yamooWikiDatabase : YamooWikiDatabase? = null

        fun getInstance(context: Context) : YamooWikiDatabase {
            if(yamooWikiDatabase == null) {
                yamooWikiDatabase = Room.databaseBuilder(
                    context,
                    YamooWikiDatabase::class.java,
                    databaseName).
                fallbackToDestructiveMigration().build()
            }
            return yamooWikiDatabase!!
        }
    }
}