package com.ecommerce.ui

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ecommerce.R
import com.ecommerce.common.BUNDLE_ACTIVITY_NAME
import com.ecommerce.common.BaseActivity
import com.ecommerce.databinding.ActivityWIPBinding

class WIPActivity : BaseActivity() {
    private lateinit var binding: ActivityWIPBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_w_i_p)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.text = intent.getStringExtra(BUNDLE_ACTIVITY_NAME)
        Toast.makeText(this, getString(R.string.text_wip), Toast.LENGTH_LONG).show()
    }
}