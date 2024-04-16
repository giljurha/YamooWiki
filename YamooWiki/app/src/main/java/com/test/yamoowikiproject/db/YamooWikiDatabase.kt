package com.test.yamoowikiproject.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(UserEntity::class, OpenPostEntity::class), version = 1)
abstract class YamooWikiDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getOpenPostDao(): OpenPostDao


    companion object {

        var yamooWikiDatabase: YamooWikiDatabase? = null

        fun getInstance(context: Context): YamooWikiDatabase {
            synchronized(this){
                if (yamooWikiDatabase == null) {
                    yamooWikiDatabase = Room
                        .databaseBuilder(
                        context = context,
                        klass = YamooWikiDatabase::class.java,
                        name = "yamooWikiDb"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return yamooWikiDatabase!!
        }
    }
}


