package com.ecommerce.ui.product_listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecommerce.data.network.ProductService
import com.ecommerce.data.repository.ProductRepository

/**
 * Created by Chetan on 09/08/20.
 */

class ProductListingViewModelFactory(private val service: ProductService) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListingViewModel::class.java)) {
            return ProductListingViewModel(
                repository = ProductRepository(service = service)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}