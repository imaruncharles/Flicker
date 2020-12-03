package com.onecupcode.flicker.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey @field:SerializedName("id") val id: String,
    @field:SerializedName("farm") val farm:Int,
    @field:SerializedName("isfamily") val isfamily:Int,
    @field:SerializedName("isfriend") val isfriend:Int,
    @field:SerializedName("ispublic") val ispublic:Int,
    @field:SerializedName("owner") val owner:String,
    @field:SerializedName("secret") val secret:String,
    @field:SerializedName("server") val server:String,
    @field:SerializedName("title") val title:String,
    var path: String?)