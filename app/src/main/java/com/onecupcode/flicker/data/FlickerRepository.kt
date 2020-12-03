package com.onecupcode.flicker.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.onecupcode.flicker.api.FlickerServices
import com.onecupcode.flicker.db.PhotoDatabase
import com.onecupcode.flicker.model.Photo
import kotlinx.coroutines.flow.Flow

class FlickerRepository (
    private val service : FlickerServices,
    private val database: PhotoDatabase)  {

    fun getPhotoResult(title : String) : Flow<PagingData<Photo>>{


        if (title.isEmpty()){
            val paginSourceFactory =   {database.photosDao().getPhotos()}

            return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
                remoteMediator = FlickerRemoteMediator(
                    "",
                    service,
                    database
                ),
                pagingSourceFactory = paginSourceFactory).flow
        }else{
            val paginSourceFactory =   {database.photosDao().searchPhoto(title)}

            return Pager(config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
                remoteMediator = FlickerRemoteMediator(
                    title,
                    service,
                    database
                ),
                pagingSourceFactory = paginSourceFactory).flow
        }


    }



    companion object {
        private const val NETWORK_PAGE_SIZE = 5
    }

}