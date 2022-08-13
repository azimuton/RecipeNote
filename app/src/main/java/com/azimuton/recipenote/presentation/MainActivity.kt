package com.azimuton.recipenote.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azimuton.recipenote.R
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdMob()
        (application as AppMainState).showAdIfAvailable(this){

        }
        Glide.with(this)
            .asGif()
            .load(R.drawable.vegetables)
            .into(ivMainVegetables);
        btMainNote.setOnClickListener {
            val intent = Intent(this, NoteActivity :: class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        adView?.resume()
    }
    override fun onPause() {
        super.onPause()
        adView?.pause()
    }
    override fun onDestroy() {
        super.onDestroy()
        adView?.destroy()
    }
    private fun initAdMob(){
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        adView?.loadAd(adRequest)
    }
}