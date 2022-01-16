package com.example.sunstone.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunstone.R
import com.example.sunstone.interfaces.ItemClickListener
import com.example.sunstone.models.Photo

class ResponseAdapter(
    private val mContext: Context,
    private val wallpaperList: List<Photo>,
    private val recyclerViewClickInterface: ItemClickListener
) :
    RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false)
        return ResponseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
        Glide.with(mContext)
            .load(wallpaperList[position].src.medium)
            .into(holder.mWallpaper)
    }

    override fun getItemCount(): Int {
        return wallpaperList.size
    }

    inner class ResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mWallpaper: ImageView
        init {
            mWallpaper = itemView.findViewById(R.id.wallpaperImage)
            mWallpaper.setOnClickListener { view: View? ->
                recyclerViewClickInterface.onItemClick(
                    adapterPosition
                )
            }
        }
    }
}