package com.ecommerce.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.adapter.ProductListAdapter.ProductViewHolder
import com.ecommerce.data.models.Product
import com.ecommerce.databinding.ListItemProductBinding
import java.util.*

/**
 * Created by Chetan on 09/08/20.
 */
class ProductListAdapter(
    private val productList: ArrayList<Product>,
    private val listener: ProductListener
) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position], position, listener)
    }

    class ProductViewHolder(private val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            product: Product,
            position: Int,
            listener: ProductListener
        ) {
            binding.product = product
            binding.position = position
            binding.listener = listener
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemProductBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }
}

class ProductListener(val clickListener: (product: Product, position: Int) -> Unit) {
    fun onClick(product: Product, position: Int) = clickListener(product, position)
}