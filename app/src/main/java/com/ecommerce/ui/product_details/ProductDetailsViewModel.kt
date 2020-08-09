package com.ecommerce.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ecommerce.data.network.APIStatus
import com.ecommerce.data.repository.ProductRepository
import kotlinx.coroutines.Dispatchers

/**
 * Created by Chetan on 09/08/20.
 */
class ProductDetailsViewModel(private val repository: ProductRepository) : ViewModel() {
    fun getProductDetails(productId: Int) = liveData(Dispatchers.IO) {
        emit(APIStatus.loading(apiResponse = null))
        try {
            emit(APIStatus.success(apiResponse = repository.getProductDetails(productId = productId)))
        } catch (e: Exception) {
            emit(APIStatus.failure(apiResponse = null))
        }
    }
}