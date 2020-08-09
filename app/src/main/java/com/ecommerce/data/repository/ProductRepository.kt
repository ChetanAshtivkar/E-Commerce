package com.ecommerce.data.repository

import com.ecommerce.data.network.ProductService

/**
 * Created by Chetan on 09/08/20.
 */
class ProductRepository(private val service: ProductService) {

    suspend fun getAllProducts() = service.getProductList()

    suspend fun getProductDetails(productId: Int) = service.getProductDetails(productId)
}