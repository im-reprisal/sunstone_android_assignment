package com.example.sunstone.ui

import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sunstone.R
import kotlinx.android.synthetic.main.activity_full_screen.*
import java.io.IOException

class FullScreenActivity : AppCompatActivity() {
    private var imgUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        setWallpaper?.setOnClickListener(View.OnClickListener { setHomeScreen() })
        imgUrl = intent.getStringExtra("imageUrl")
        loadImage(imgUrl)
    }

    private fun loadImage(imgUrl: String?) {
        Glide.with(this).load(imgUrl).into(full_image!!)
    }

    private fun setHomeScreen() {
        val wallpaperManager = WallpaperManager.getInstance(applicationContext)
        val bitmap = (full_image!!.drawable as BitmapDrawable).bitmap
        try {
            wallpaperManager.setBitmap(bitmap)
            Toast.makeText(applicationContext, "Setting Wallpaper", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}