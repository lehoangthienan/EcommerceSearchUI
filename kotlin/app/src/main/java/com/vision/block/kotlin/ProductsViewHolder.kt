package com.vision.block.kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_product.view.*

class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(
        context: Context,
        product: Product
    ) {
        loadImage(context, product, itemView)
        loadText(product, itemView)
    }

    @SuppressLint("SetTextI18n")
    private fun loadText(product: Product, itemView: View) {
        itemView.tvName.text = product.name
        itemView.tvPrice.text = product.price
        itemView.tvBranchName.text = product.content
        itemView.tvAddress.text = product.address
        itemView.tvDistance.text = product.distance
    }

    private fun loadImage(context: Context, product: Product, itemView: View?) {
        itemView?.ivProduct?.let {
            Glide.with(context)
                .asBitmap()
                .load(product.image)
                .into(it)
        }
    }
}