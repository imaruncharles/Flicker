package com.onecupcode.flicker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onecupcode.flicker.R
import com.onecupcode.flicker.model.Photo

class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title : TextView = view.findViewById(R.id.title)
    private val image: AppCompatImageView = view.findViewById(R.id.image)
//https://live.staticflickr.com/{server-id}/{id}_{secret}.jpg
    private var photo: Photo? = null

   fun bind(photo: Photo){
       showPhoto(photo)
   }

    private fun showPhoto(photo: Photo){
        this.photo = photo
        title.text = photo.title

        val url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"

        Glide
            .with(this.image.context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_cloud_download_24)
            .into(this.image)
    }

    companion object{
        fun create(parent: ViewGroup): PhotoViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_view,parent,false)
            return PhotoViewHolder(view)

        }
    }

}