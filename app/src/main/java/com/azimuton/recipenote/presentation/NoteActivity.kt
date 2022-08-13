package com.azimuton.recipenote.presentation

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azimuton.recipenote.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        initAdMob2()
        fabAddNote.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, R.anim.open_activity)
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        adView2?.resume()
    }
    override fun onPause() {
        super.onPause()
        adView2?.pause()
    }
    override fun onDestroy() {
        super.onDestroy()
        adView2?.destroy()
    }
    private fun initAdMob2(){
        MobileAds.initialize(this)
        val adRequest2 = AdRequest.Builder().build()
        adView2?.loadAd(adRequest2)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, R.anim.open_activity)
        finish()
    }
}