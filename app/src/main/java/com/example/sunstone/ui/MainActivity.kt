package com.example.sunstone.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunstone.R
import com.example.sunstone.api.RetrofitGenerator
import com.example.sunstone.extras.Constants.API_KEY
import com.example.sunstone.extras.Constants.PER_PAGE
import com.example.sunstone.extras.Constants.START_PAGE
import com.example.sunstone.interfaces.ItemClickListener
import com.example.sunstone.models.Photo
import com.example.sunstone.models.ResponseModel
import com.example.sunstone.ui.adapter.ResponseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity(), ItemClickListener {
    private val wallpaperList: MutableList<Photo> = ArrayList<Photo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView?.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView?.setLayoutManager(gridLayoutManager)
        fetchData(START_PAGE)
    }

    private fun fetchData(pageCount: Int) {
        val call: Call<ResponseModel?>? = RetrofitGenerator
            .getInstance()
            .getApi().getWallpaper(API_KEY, pageCount, PER_PAGE)
        call?.enqueue(object : Callback<ResponseModel?> {
            override fun onResponse(
                call: Call<ResponseModel?>,
                response: Response<ResponseModel?>
            ) {
                val wallpaperResponse = response.body()
                if (response.isSuccessful && wallpaperResponse != null) {
                    wallpaperList.addAll(wallpaperResponse.photos)
                    val wallpaperAdapter =
                        ResponseAdapter(applicationContext, wallpaperList, this@MainActivity)
                    recyclerView!!.adapter = wallpaperAdapter
                    wallpaperAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onItemClick(position: Int) {
        val intent = Intent(applicationContext, FullScreenActivity::class.java)
        intent.putExtra("imageUrl", wallpaperList[position].src.large)
        startActivity(intent)
    }
}