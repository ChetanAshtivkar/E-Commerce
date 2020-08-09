package com.ecommerce.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ecommerce.R

/**
 * Created by Chetan on 09/08/20.
 */
@BindingAdapter("set_picture")
fun setPicture(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .fitCenter()
        .placeholder(R.drawable.placeholder)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("set_quantity")
fun setQuantity(textView: TextView, quantity: Int?) {
    textView.text = quantity?.let {
        "QTY: $quantity"
    } ?: run {
        "Not available"
    }
}

@BindingAdapter("set_price")
fun setPrice(textView: TextView, price: Int?) {
    textView.text = price?.let {
        "\u20B9 $price"
    } ?: run {
        "Price not available"
    }
}

@BindingAdapter("set_product_id")
fun setProductId(textView: TextView, id: Int?) {
    id?.let {
        textView.text = " (Product Id: $id)"
    }
}

@BindingAdapter("set_size")
fun setSize(textView: TextView, size: String?) {
    textView.text = size?.let {
        "Size available: $size"
    } ?: run {
        "Free size"
    }
}