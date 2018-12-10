package com.vision.block.kotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ProductsAdapter(
    private var products: ArrayList<Product>
) : RecyclerView.Adapter<ProductsViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        context = parent.context
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindData(
            context,
            products[products.size - position - 1]
        )
    }

    interface OnItemClickedListener {
        fun onItemClicked(id: String)
    }
}
