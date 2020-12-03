package com.onecupcode.flicker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.onecupcode.flicker.data.FlickerRepository
import com.onecupcode.flicker.model.Photo
import kotlinx.coroutines.flow.Flow


class PhotoRepositoryViewmodel(private val reository: FlickerRepository) : ViewModel(){

    private var currentSearchResult: Flow<PagingData<Photo>>? = null


    fun getPhotoList(title : String) : Flow<PagingData<Photo>> {
        val newResult: Flow<PagingData<Photo>> = reository.getPhotoResult(title)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }





}

