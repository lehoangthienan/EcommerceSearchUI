package com.vision.block.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var gmap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        setDismissProgressLoadMapDialog()
    }

    private fun setDismissProgressLoadMapDialog() {
        gmap.setOnMapLoadedCallback {
            progressBarAddLocation.visibility = View.GONE
        }
    }

    override fun setUpView(view: View, savedInstanceState: Bundle?) {
        mapViewAddressSearch.onCreate(savedInstanceState)
        mapViewAddressSearch.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        mapViewAddressSearch?.onResume()
    }

    override fun onPause() {
        mapViewAddressSearch?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapViewAddressSearch?.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapViewAddressSearch?.onLowMemory()
    }

}