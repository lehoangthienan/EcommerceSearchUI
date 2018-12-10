package com.vision.block.kotlin

data class Product(
    var name: String? = null,
    var price: String? = null,
    var content: String? = null,
    var image: String? = null,
    var lat: Double? = null,
    var lng: Double? = null,
    var owner: String? = null
)