package com.onecupcode.flicker.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.onecupcode.flicker.model.Photo

class PhotoAdapter : PagingDataAdapter<Photo, RecyclerView.ViewHolder>(PHOTO_COMPARATOR) {

    companion object{
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return (oldItem.title == newItem.title)
            }
            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val uiModel = getItem(position)

        uiModel.let {
            if (it != null) {
                (holder as PhotoViewHolder).bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder.create(parent)
    }
}