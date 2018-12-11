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
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.layout_custom_marker.view.*

class LocationCustom {

    fun showMemberLocationOnMap(
        googleMap: GoogleMap?,
        avatar: String,
        lat: Double,
        lng: Double,
        viewMemberLocation: View?,
        name: String
    ) {
        renderImage(viewMemberLocation?.context!!, avatar, object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                viewMemberLocation.ivMarker.setImageBitmap(resource)
                val icon = BitmapDescriptorFactory.fromBitmap(viewMemberLocation.convertToBitmap())
                val marker: Marker = googleMap?.addMarker(
                    MarkerOptions().position(
                        LatLng(
                            lat,
                            lng
                        )
                    ).title(name).snippet("Sản Xuất tại Trung Quốc nhập khẩu qua Việt Nam")
                        .icon(icon)
                )!!
                marker.tag = R.layout.tag_marker
                marker.showInfoWindow()
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

//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
//android:layout_width="58dp"
//android:layout_height="58dp">
//
//
//<de.hdodenhof.circleimageview.CircleImageView
//android:id="@+id/ivMarker"
//android:layout_width="30dp"
//android:src="@drawable/ic_user"
//android:layout_height="30dp"
//android:layout_alignParentTop="true" android:layout_marginTop="6dp" android:layout_alignParentStart="true"
//android:layout_marginStart="15dp"/>
//
//<RelativeLayout
//android:layout_width="58dp"
//android:layout_height="58dp"
//android:background="@drawable/ic_placeholder"
//android:layout_alignParentStart="true" android:layout_alignParentTop="true"/>
//
//
//</RelativeLayout>