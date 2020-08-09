package com.ecommerce.data.models

import com.google.gson.annotations.SerializedName

data class SortProperties(
    @SerializedName("A") val a: Int,
    @SerializedName("B") val b: Int,
    @SerializedName("C") val c: Int
)