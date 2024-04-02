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
        val databaseName = "yamooWikiDb"
        var yamooWikiDatabase: YamooWikiDatabase? = null

        fun getInstance(context: Context): YamooWikiDatabase {
            synchronized(this){
                if (yamooWikiDatabase == null) {
                    yamooWikiDatabase = Room.databaseBuilder(
                        context,
                        YamooWikiDatabase::class.java,
                        databaseName
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return yamooWikiDatabase!!
        }
    }
}

/*
// 이미지 파일을 읽고 쓰는 도우미 함수 정의
object ImageUtils {
    fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream)
        return outputStream.toByteArray()
    }

    fun convertByteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}

// 이미지 파일을 Room 데이터베이스에 쓰는 함수 정의
fun saveImageToDatabase(context: Context, bitmap: Bitmap) {
    val imageData = ImageUtils.convertBitmapToByteArray(bitmap)
    val imageEntity = ImageEntity(imageData = imageData)

    val database = androidx.room.Room.databaseBuilder(
        context.applicationContext,
        ImageDatabase::class.java, "image-database"
    ).build()

    database.imageDao().insert(imageEntity)
}

// Room 데이터베이스에서 이미지 파일을 읽어오는 함수 정의
fun loadImageFromDatabase(context: Context, imageId: Long): Bitmap? {
    val database = androidx.room.Room.databaseBuilder(
        context.applicationContext,
        ImageDatabase::class.java, "image-database"
    ).build()

    val imageEntity = database.imageDao().getImageById(imageId)
    return if (imageEntity != null) {
        ImageUtils.convertByteArrayToBitmap(imageEntity.imageData)
    } else {
        null
    }
}
 */
