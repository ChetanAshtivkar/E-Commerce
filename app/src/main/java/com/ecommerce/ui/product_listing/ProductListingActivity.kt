package com.ecommerce.ui.product_listing

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ecommerce.R
import com.ecommerce.adapter.ProductListAdapter
import com.ecommerce.adapter.ProductListener
import com.ecommerce.common.BUNDLE_PRODUCT_ID
import com.ecommerce.common.BaseActivity
import com.ecommerce.data.models.Product
import com.ecommerce.data.network.RetrofitFactory
import com.ecommerce.data.network.Status
import com.ecommerce.databinding.ActivityProductListingBinding
import com.ecommerce.ui.product_details.ProductDetailsActivity
import com.google.android.material.snackbar.Snackbar
import java.util.*

/**
 * Created by Chetan on 08/08/20.
 */
class ProductListingActivity : BaseActivity() {

    private lateinit var binding: ActivityProductListingBinding
    private lateinit var viewModel: ProductListingViewModel
    private lateinit var adapter: ProductListAdapter
    private val productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_listing)
        setSupportActionBar(binding.appBarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this,
            ProductListingViewModelFactory(service = RetrofitFactory.makeRetrofitService())
        ).get(ProductListingViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.appBarLayout.contentMain.swipeToRefresh.setOnRefreshListener {
            getProducts()
        }

        setupDrawer()
        setRecyclerViewAdapter()
        getProducts()
        setSortButtons()
    }

    private fun setupDrawer() {

        val drawerToggle =
            ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


    }

    private fun setSortButtons() {
        binding.appBarLayout.contentMain.sortByA.setOnCheckedChangeListener { _, checked ->
            productList.sortWith(kotlin.Comparator { lhs, rhs ->
                if (checked) {
                    when {
                        lhs.sortProperties.a > rhs.sortProperties.a -> 1
                        lhs.sortProperties.a < rhs.sortProperties.a -> -1
                        else -> 0
                    }
                } else {
                    when {
                        lhs.sortProperties.a < rhs.sortProperties.a -> 1
                        lhs.sortProperties.a > rhs.sortProperties.a -> -1
                        else -> 0
                    }
                }
            })
            adapter.notifyDataSetChanged()
        }

        binding.appBarLayout.contentMain.sortByB.setOnCheckedChangeListener { _, checked ->
            productList.sortWith(kotlin.Comparator { lhs, rhs ->
                if (checked) {
                    when {
                        lhs.sortProperties.a > rhs.sortProperties.a -> 1
                        lhs.sortProperties.a < rhs.sortProperties.a -> -1
                        else -> 0
                    }
                } else {
                    when {
                        lhs.sortProperties.a < rhs.sortProperties.a -> 1
                        lhs.sortProperties.a > rhs.sortProperties.a -> -1
                        else -> 0
                    }
                }
            })
            adapter.notifyDataSetChanged()
        }

        binding.appBarLayout.contentMain.sortByC.setOnCheckedChangeListener { _, checked ->
            productList.sortWith(kotlin.Comparator { lhs, rhs ->
                if (checked) {
                    when {
                        lhs.sortProperties.a > rhs.sortProperties.a -> 1
                        lhs.sortProperties.a < rhs.sortProperties.a -> -1
                        else -> 0
                    }
                } else {
                    when {
                        lhs.sortProperties.a < rhs.sortProperties.a -> 1
                        lhs.sortProperties.a > rhs.sortProperties.a -> -1
                        else -> 0
                    }
                }
            })
            adapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerViewAdapter() {
        adapter = ProductListAdapter(productList, ProductListener { product, _ ->
            val bundle = Bundle()
            bundle.putSerializable(BUNDLE_PRODUCT_ID, product.id)
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
        })
        binding.appBarLayout.contentMain.productList.adapter = adapter
    }

    private fun getProducts() {
        viewModel.getProductList().observe(this@ProductListingActivity, Observer {
            it.let { response ->
                when (response.status) {
                    Status.LOADING -> {
                        binding.appBarLayout.contentMain.progressBar.show()
                    }

                    Status.SUCCESS -> {
                        binding.appBarLayout.contentMain.progressBar.hide()
                        binding.appBarLayout.contentMain.swipeToRefresh.isRefreshing = false
                        productList.clear()
                        response.apiResponse?.data?.let { list -> productList.addAll(list) }
                        adapter.notifyDataSetChanged()
                    }

                    Status.FAILURE -> {
                        binding.appBarLayout.contentMain.progressBar.hide()
                        binding.appBarLayout.contentMain.swipeToRefresh.isRefreshing = false
                        val snackBar: Snackbar = Snackbar
                            .make(binding.root, getString(R.string.error), Snackbar.LENGTH_LONG)
                            .setAction(
                                getString(R.string.try_again)
                            ) {
                                getProducts()
                            }
                        snackBar.show()
                    }
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            R.id.nav_my_account -> {
                return true
            }
            R.id.nav_offers -> {
                return true
            }
            R.id.nav_my_orders -> {
                return true
            }
            R.id.nav_logout -> {
                Toast.makeText(this, getString(R.string.text_wip), Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}