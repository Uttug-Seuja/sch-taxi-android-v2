package com.sch.data.model.remote.request

import com.google.gson.annotations.SerializedName


data class PostRegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("profilePath") val profilePath: String,
    @SerializedName("schEmail") val schEmail: String,
)