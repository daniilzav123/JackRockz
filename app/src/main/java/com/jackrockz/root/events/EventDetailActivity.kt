package com.jackrockz.root.events

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.jackrockz.MyApplication
import com.jackrockz.R
import com.jackrockz.api.EventModel
import com.jackrockz.api.GalleryModel
import com.jackrockz.api.VisitorModel
import com.jackrockz.commons.RxBaseActivity
import com.jackrockz.onboarding.adapter.CityAdapter
import com.jackrockz.utils.Utils
import kotlinx.android.synthetic.main.activity_event_detail.*
import kotlinx.android.synthetic.main.description.*
import kotlinx.android.synthetic.main.image_slider.*
import kotlinx.android.synthetic.main.who_layout.*

class EventDetailActivity : RxBaseActivity(), View.OnClickListener {
    lateinit var event: EventModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        event = MyApplication.instance.currentEvent

        supportActionBar!!.subtitle = Utils.getStringFromTwoDates(event.start_date, event.end_date)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        txtRegularPrice.paintFlags = txtRegularPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        btnGet.setOnClickListener(this)

        txtTitle.text = event.title
        txtSubtitle.text = event.subtitle
        txtViewsCount.text = event.views_count.toString() + "x"
        txtExpCount.text = event.experience_count.toString() + "x"
        txtGuestCount.text = event.guestlist_count.toString() + "x"
        txtPrice.text = event.price
        txtRegularPrice.text = event.regular_price
        txtDescription.text = event.description

        if (event.visitors != null) {
            layout_who.visibility = View.VISIBLE
            who_recycler_view.apply {
                setHasFixedSize(true)
                val linearLayout = LinearLayoutManager(context)
                linearLayout.orientation = LinearLayoutManager.HORIZONTAL
                layoutManager = linearLayout
                adapter = WhoAdapter(this@EventDetailActivity, event.visitors as ArrayList<VisitorModel>)
            }
        }

        if (event.gallery?.items != null) {
            viewpager.adapter = ImagePagerAdapter(this, event.gallery!!.items!! as ArrayList<GalleryModel>)
            indicator.setViewPager(viewpager)
        }
        supportActionBar!!.title = event.venue.name

//        try{
//            initMap()
//        } catch (e: Exception) {
//
//        }
    }

    override fun onResume() {
        super.onResume()
//        initMap()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnGet -> {
                startActivity(Intent(this, EventPaymentActivity::class.java))
                this.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }
}
