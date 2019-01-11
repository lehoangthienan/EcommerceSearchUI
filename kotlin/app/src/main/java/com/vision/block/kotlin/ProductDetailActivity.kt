package com.vision.block.kotlin

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.smarteist.autoimageslider.SliderLayout
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_product_detail.*


class ProductDetailActivity : AppCompatActivity() {

    private var isCheck = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        initView()

        ivProduct.setIndicatorAnimation(SliderLayout.Animations.FILL) //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        ivProduct.scrollTimeInSec = 1 //set scroll delay in seconds :

        setSliderViews()

        addEvents()
    }

    private fun addEvents() {
        tvBack.setOnClickListener {
            finish()
        }
        btPhoneNumber.setOnClickListener {
            isCheck= !isCheck
            if(!isCheck) btPhoneNumber.text = "(+84)973043044"
            else btPhoneNumber.text = "TOUCH TO SHOW PHONEN NUMBER"
        }
    }

    private fun initView() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }

    private fun setSliderViews() {

        for (i in 0..3) {

            val sliderView = SliderView(this)

            when (i) {
                0 -> sliderView.imageUrl =
                        "https://cdn1.itpro.co.uk/sites/itpro/files/styles/gallery_adv/public/2018/08/apple_macbook_pro_13_back_look.jpg?itok=kHIF2fhd"
                1 -> sliderView.imageUrl =
                        "https://support.apple.com/library/content/dam/edam/applecare/images/en_US/macbookpro/macos-sierra-macbook-pro-touch-bar-animation-hero.gif"
                2 -> sliderView.imageUrl =
                        "https://www.intego.com/mac-security-blog/wp-content/uploads/2018/09/customize-touch-bar-macbook-pro-1024x576.jpg"
                3 -> sliderView.imageUrl =
                        "https://icdn2.digitaltrends.com/image/macbook-pro-2016-keyboard-1500x1000.jpg?ver=1"
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            sliderView.setDescription("NO. " + (i + 1))
            sliderView.setOnSliderClickListener {
                Toast.makeText(
                    this@ProductDetailActivity,
                    "Macbook is best " + (i + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //at last add this view in your layout :
            ivProduct.addSliderView(sliderView)
        }
    }
}
