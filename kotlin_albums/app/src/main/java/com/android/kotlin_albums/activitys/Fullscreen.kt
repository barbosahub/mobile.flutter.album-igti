package com.android.kotlin_albums.activitys

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.android.kotlin_albums.R

class Fullscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        val intent = intent
        val url = intent.getStringExtra(MainActivity.URL)

        val mImage = findViewById<View>(R.id.imagefull) as ImageView

        mImage.load(url)
    }
}