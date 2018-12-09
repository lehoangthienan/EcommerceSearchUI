package com.vision.block.kotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.layout_custom_marker.view.*

class LocationCustom {

    fun showMemberLocationOnMap(
        googleMap: GoogleMap?,
        avatar: String,
        lat: Double,
        lng: Double,
        viewMemberLocation: View?
    ) {
        renderImage(viewMemberLocation?.context!!, avatar, object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                viewMemberLocation.ivMarker.setImageBitmap(resource)
                val icon = BitmapDescriptorFactory.fromBitmap(viewMemberLocation.convertToBitmap())
                googleMap?.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            lat,
                            lng
                        )
                    ).title("Macbook Pro 2018").snippet("Sản Xuất tại Trung Quốc nhập khẩu qua Việt Nam")
                        .icon(icon)
                )
            }
        })
    }

    fun renderImage(context: Context, avatar: String, target: SimpleTarget<Bitmap>) {
        Glide.with(context)
            .asBitmap()
            .load(avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(target)

    }

    fun View?.convertToBitmap(): Bitmap? {

        this?.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        var bitmap: Bitmap? = null
        this?.let {
            bitmap = Bitmap.createBitmap(it.measuredWidth, it.measuredHeight, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(bitmap)
        this?.layout(0, 0, this.measuredWidth, this.measuredHeight)
        this?.draw(canvas)
        return bitmap
    }

}