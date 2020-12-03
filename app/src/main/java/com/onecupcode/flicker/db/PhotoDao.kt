package com.onecupcode.flicker.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.onecupcode.flicker.model.Photo

@Dao
interface PhotoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(photos: List<Photo>)

    @Query("DELETE FROM photo")
    suspend fun clearPhotos()

    @Query("SELECT * FROM photo")
    fun getPhotos() : PagingSource<Int,Photo>

    @Query("SELECT * FROM photo WHERE title LIKE :title")
    fun searchPhoto( title: String) : PagingSource<Int,Photo>
}