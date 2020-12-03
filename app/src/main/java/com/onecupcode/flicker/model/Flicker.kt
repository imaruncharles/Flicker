package com.onecupcode.flicker.model

import com.google.gson.annotations.SerializedName

data class Flicker(
    @SerializedName("page") val page:Int,
    @SerializedName("total") val total : Int,
    @SerializedName("photo") val photo : List<Photo>)