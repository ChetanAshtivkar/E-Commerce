package com.ecommerce.data.network

import com.ecommerce.data.models.Product
import com.google.gson.annotations.SerializedName

/**
 * Created by Chetan on 09/08/20.
 */
data class ListApiResponse(
    @SerializedName("data") val data: List<Product>
)