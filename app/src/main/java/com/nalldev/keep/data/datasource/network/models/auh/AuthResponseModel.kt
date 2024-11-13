package com.nalldev.keep.data.datasource.network.models.auh

import com.google.gson.annotations.SerializedName

data class AuthResponseModel(

	@field:SerializedName("data")
	val data: AuthData? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class AuthData(
	@field:SerializedName("token")
	val token: String? = null
)
