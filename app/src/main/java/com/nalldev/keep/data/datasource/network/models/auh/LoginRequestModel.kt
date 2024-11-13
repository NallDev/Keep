package com.nalldev.keep.data.datasource.network.models.auh

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @field:SerializedName("username")
    val username: String,
    @field:SerializedName("password")
    val password: String
)
