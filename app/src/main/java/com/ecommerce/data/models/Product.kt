package com.ecommerce.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Chetan on 09/08/20.
 */
data class Product(
    @SerializedName("id") val id: Int,
    @SerializedName("category_id") val categoryId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("category_name") val categoryName: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("price") val price: Int?,
    @SerializedName("qty") val quantityAvailable: Int?,
    @SerializedName("image") val imageUrl: String?,
    @SerializedName("size") val size: String?,
    @SerializedName("sort_props") val sortProperties: SortProperties
)

