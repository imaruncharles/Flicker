package com.onecupcode.flicker.api

import com.google.gson.annotations.SerializedName
import com.onecupcode.flicker.model.Flicker

data class FlickerResponse(@SerializedName("stat") val stat: String,
                           @SerializedName("photos") val flicker: Flicker,
                           val nextPage: Int? = null)