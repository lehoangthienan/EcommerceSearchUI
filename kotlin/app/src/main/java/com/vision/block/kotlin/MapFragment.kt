package com.vision.block.kotlin

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.bottom_sheet_products.*
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.item_product.*


class MapFragment : BaseFragment(), OnMapReadyCallback {

    private val locationCustom = LocationCustom()
    private var gmap: GoogleMap? = null
    private var viewLocation: View? = null
    private var products = ArrayList<Product>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewLocation = inflater.inflate(R.layout.layout_custom_marker, container, false)
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadProductsToRecyclerView()
        addFakeData()
        addEvents()
    }

    private fun loadProductsToRecyclerView() {
        try {
            rvProducts.apply {
                this.layoutManager = LinearLayoutManager(activity)
                this.adapter = ProductsAdapter(products)
            }

        } catch (e: Exception) {
        }
    }

    private fun addFakeData() {
        products.add(
            Product(
                "Macbook Pro 2018",
                "22.000.000VND",
                "Nice goods like new, test bags",
                "https://macbookshop.vn/wp-content/uploads/2018/07/Macbook-pro-13inch-2018-TouchBar-SILVER.jpeg",
                10.869251,
                106.801489,
                "Lê Hoàng Thiên Ấn",
                "1.1 km",
                "123 Hung Vuong, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Iphone Xs Max 256gb",
                "12.000.000VND",
                "Just bought 5 days, change the other machine",
                "https://zdnet4.cbsistatic.com/hub/i/2018/09/21/7d30bae4-58d0-4168-87f3-6ec65630aa31/b1a7c19baa291431560da3d607cf6ffe/iphone-xs-max.jpg",
                10.864005,
                106.795705,
                "Lê Hoàng Thiên Ấn",
                "2.5 km",
                "99 Tran Hung Dao, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Husky dog of Alaska",
                "8.000.000VND",
                "Bad dogs should sell, it eats too much",
                "http://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Le%C3%AFko_au_bois_de_la_Cambre.jpg/440px-Le%C3%AFko_au_bois_de_la_Cambre.jpg",
                10.875063,
                106.801398,
                "Trần Văn Rin",
                "1.5 km",
                "330, Ngo Quyen, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Mazda 6 2.5L 2019",
                "22.000.000VND",
                "Running cars, over 5000km, new hard",
                "https://i.ytimg.com/vi/KeYSjZfojbo/maxresdefault.jpg",
                10.868152,
                106.795519,
                "Lê Hoàng Thiên Ấn",
                "4.0 km",
                "99 Doc Lap, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Honda SH 150i ABS",
                "100.000.000VND",
                "Winning cars, sold to anyone in need",
                "http://www.motorcyclelife.com.au/wp-content/uploads/2017/02/2017-Honda-SH150.jpg",
                10.865159,
                106.804788,
                "Lê Giô Na",
                "3.3 km",
                "56 Vo Van Ngan, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Casio Edifice FF G123",
                "15.000.000VND",
                "Fake households sell for more than a day",
                "https://donghosaigon.vn/wp-content/uploads/2017/03/dong-ho-nam-deo-tay-casio-edifice-efr-552d-1a3vudf-chinh-hang-tphcm.jpg",
                10.870554,
                106.808608,
                "Lê Hoàng Thiên Ấn",
                "3.2 km",
                "78 Hoang Dieu 2, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Loa Xiaomi square box 2",
                "700.000VND",
                "Loud speakers should use less\n",
                "https://cellphones.com.vn/media/wysiwyg/accessories/speaker/loa-xiaomi-square-box-2-1.jpg",
                10.861198,
                106.798265,
                "Đặng Việt Dũng",
                "4.5 km",
                "23 Doan Ket, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Kitty for sleep",
                "200.000VND",
                "Less used should be sold to anyone who needs it",
                "https://cdn.concung.com/25457-24709-gtt_large/ly-co-quai-superware-hoa-tiet-hello-kitty-c352-2.jpg",
                10.858837,
                106.798823,
                "Lê Hoàng Thiên Ấn",
                "5.0 km",
                "77 Man Thien, District 9, HCM"
            )
        )
        products.add(
            Product(
                "Shiba",
                "1.000.000VND",
                "Bad dogs should be sold",
                "https://gaubongdep.com/wp-content/uploads/2017/07/hinh-san-pham-goi-tron-cho-shiba-truoc.jpg",
                10.863726,
                106.804016,
                "Đặng Việt Dũng",
                "4.9 km",
                "89 Le Van Viet, District 9, HCM"
            )
        )
        products.add(
            Product(
                "Acqui GS for moto",
                "2.000.000VND",
                "New 100%",
                "http://www.gsbattery.vn/Data/Sites/1/Product/9/gt5a.jpg",
                10.857404,
                106.801484,
                "Lê Giô Na",
                "3.7 km",
                "66 Road No. 9, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Tire for moto",
                "1.500.000VND",
                "New 100%",
                "https://www.ircvietnam.com/images/product/100-90-14%20ss560f%20%20-%20100-90-14%20ss560r.jpg",
                10.863684,
                106.804788,
                "Trần Văn Rin",
                "3,7 km",
                "88 Road No. 12, Thu Duc, HCM"
            )
        )
        products.add(
            Product(
                "Honda Winner 150",
                "39.000.000VND",
                "Old cars 10,000km, accidents should sold",
                "https://media.thethao247.vn/upload/quy/2017/03/22/winner-9.JPG",
                10.862335,
                106.806505,
                "Lê Hoàng Thiên Ấn",
                "3.9 km",
                "90, Road 25, Thu Duc, HCM"
            )
        )
    }

    private fun addEvents() {
        floatingActionButton.setOnClickListener {
            showCurrentLocation()
        }
    }

    private fun addMarker() {
        products.map { product ->
            locationCustom.showMemberLocationOnMap(
                gmap,
                product.image!!,
                product.lat!!,
                product.lng!!,
                viewLocation,
                product.name!!
            )
        }
        val latLng = LatLng(10.869251, 106.801489)
        gmap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
//        gmap?.isMyLocationEnabled = true
//        gmap?.uiSettings?.isMyLocationButtonEnabled = true
        setDismissProgressLoadMapDialog()
        addMarker()
        showCurrentLocation()
    }

    private fun showCurrentLocation() {
        val locationManager = activity!!.getSystemService(LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        val bestProvider = locationManager.getBestProvider(criteria, true)
        if (ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        val location = locationManager.getLastKnownLocation(bestProvider)
        if (location != null) {
            Log.d("LocationXXX", location.toString())
            onLocationChanged(location)
        }
    }

    fun onLocationChanged(location: Location?) {
        Log.d("LocationXXX", location.toString())
        val vdCurrentNotShadow = activity?.getDrawable(R.drawable.ic_currentlocation_noshadow)
        vdCurrentNotShadow?.setBounds(0, 0, 50, 50)
        val bmCurrentNotShadow = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888)
        val cvCurrentNotShadow = Canvas(bmCurrentNotShadow)
        vdCurrentNotShadow?.draw(cvCurrentNotShadow)
        val bdCurrentNotShadow: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bmCurrentNotShadow)

        val cdShadow = activity?.getDrawable(R.drawable.ic_shadow)
        cdShadow?.setBounds(0, 0, 700, 700)
        val bmShadow = Bitmap.createBitmap(700, 700, Bitmap.Config.ARGB_8888)
        val cvShadow = Canvas(bmShadow)
        cdShadow?.draw(cvShadow)
        val bdShadow: BitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bmShadow)

        if (location != null) {
            val latLng = LatLng(location.latitude, location.longitude)
//            gmap?.clear()
//            val marker = gmap?.addMarker(MarkerOptions().position(latLng))
//            marker?.showInfoWindow()
            gmap?.addGroundOverlay(
                GroundOverlayOptions()
                    .position(latLng, 50f)
                    .transparency(0.5f)
                    .image(bdShadow)
            )
            gmap?.addMarker(
                MarkerOptions()
                    .flat(true)
                    .icon(bdCurrentNotShadow)
                    .anchor(0.5f, 0.5f)
                    .position(latLng)
            )
        } else {
            //Toast.makeText(context, "Location not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDismissProgressLoadMapDialog() {
        gmap?.setOnMapLoadedCallback {
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