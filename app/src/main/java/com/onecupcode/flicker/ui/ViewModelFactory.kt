package com.onecupcode.flicker.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onecupcode.flicker.data.FlickerRepository

class ViewModelFactory(private val repository: FlickerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PhotoRepositoryViewmodel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PhotoRepositoryViewmodel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}