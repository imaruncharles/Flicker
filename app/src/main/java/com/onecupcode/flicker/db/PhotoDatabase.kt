package com.onecupcode.flicker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.onecupcode.flicker.model.Photo

@Database(
    entities = [Photo::class,RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoDatabase : RoomDatabase(){
    abstract fun photosDao(): PhotoDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object{

        @Volatile
        private var INSTANCE: PhotoDatabase? =null

        fun getInstance(context: Context): PhotoDatabase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            PhotoDatabase::class.java,"flicker.db")
                .build()

    }
}