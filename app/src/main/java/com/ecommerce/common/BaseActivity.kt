package com.ecommerce.common

import android.R
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Chetan on 08/08/20.
 */
const val BUNDLE_PRODUCT_ID = "com.ecommerce.BUNDLE_PRODUCT_ID"

open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}