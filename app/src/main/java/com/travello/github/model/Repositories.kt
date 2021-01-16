package com.travello.github.model

import com.google.gson.annotations.SerializedName

data class Repositories (
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("items") val repositoryList: List<Repository>
)
