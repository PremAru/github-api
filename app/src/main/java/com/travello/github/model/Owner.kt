package com.travello.github.model

import com.google.gson.annotations.SerializedName

data class Owner (
    @SerializedName("login") val userName: String,
    @SerializedName("avatar_url") val avatarUrl: String
)