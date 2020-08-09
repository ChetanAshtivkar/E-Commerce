package com.ecommerce.ui.product_details

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ecommerce.R
import com.ecommerce.common.BUNDLE_PRODUCT_ID
import com.ecommerce.common.BaseActivity
import com.ecommerce.data.models.Product
import com.ecommerce.data.network.RetrofitFactory
import com.ecommerce.data.network.Status
import com.ecommerce.databinding.ActivityProductDetailsBinding
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Chetan on 09/08/20.
 */
class ProductDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel
    private val product = MutableLiveData<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            ProductDetailsViewModelFactory(service = RetrofitFactory.makeRetrofitService())
        ).get(ProductDetailsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        intent.extras?.let {
            getDetails(it.getInt(BUNDLE_PRODUCT_ID))
        }
    }

    private fun getDetails(productId: Int) {
        viewModel.getProductDetails(productId).observe(this@ProductDetailsActivity, Observer {
            it.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                        binding.progressBar.show()
                    }

                    Status.SUCCESS -> {
                        binding.progressBar.hide()
                        product.postValue(response.apiResponse)
                    }

                    Status.FAILURE -> {
                        binding.progressBar.hide()
                        val snackBar: Snackbar = Snackbar
                            .make(binding.root, getString(R.string.error), Snackbar.LENGTH_LONG)
                            .setAction(
                                getString(R.string.try_again)
                            ) { getDetails(productId) }
                        snackBar.show()
                    }
                }
            }
        })

        product.observe(this, Observer {
            binding.product = it
        })
    }
}