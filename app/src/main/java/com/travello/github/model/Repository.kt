package com.travello.github.model

import com.google.gson.annotations.SerializedName

data class Repository (
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val starCount: String,
    @SerializedName("owner") val owner: Owner
) {
    val starCountDisplayName: String get() = "Star: $starCount"
}