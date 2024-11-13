package com.nalldev.keep.data.datasource.network.models.auh

import com.google.gson.annotations.SerializedName

data class RegisterRequestModel(
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
