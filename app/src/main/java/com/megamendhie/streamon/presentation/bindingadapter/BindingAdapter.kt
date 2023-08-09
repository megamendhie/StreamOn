package com.megamendhie.streamon.presentation.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.megamendhie.streamon.R
import com.megamendhie.streamon.utils.Commons.Companion.BASE_IMAGE_URL

/**
 * Created by Mendhie on 8/9/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */

@BindingAdapter("app:setScore")
fun setScore(txtScore: TextView, score: Double){
    txtScore.text = (score*10).toInt().toString()
}

//binding adapter to fetch poster image and render on the poster imageView
@BindingAdapter("app:setPosterImage")
fun setPosterImage(imgPoster: ImageView, path: String?){
    if(path.isNullOrEmpty())
        return
    val posterUrl = BASE_IMAGE_URL + path
    Glide
        .with(imgPoster.context)
        .load(posterUrl)
        .centerCrop()
        .placeholder(R.drawable.pic_placeholder)
        .into(imgPoster)
}

//binding adapter to fetch backdrop image and render on the backdrop imageView
@BindingAdapter("app:setBackdropImage")
fun setBackdropImage(imgBackdrop: ImageView, path: String?){
    if(path.isNullOrEmpty())
        return
    val backdropUrl = BASE_IMAGE_URL + path
    Glide
        .with(imgBackdrop.context)
        .load(backdropUrl)
        .centerCrop()
        .placeholder(R.drawable.pic_placeholder)
        .into(imgBackdrop)
}

//calculate and bind the runtime of the movie
@BindingAdapter("app:setRuntime")
fun setRuntime(txtRuntime: TextView, runtime: Int?){
    if(runtime==null)
    {txtRuntime.text = ""
        return
    }
    val hours = runtime/60
    val minutes = runtime%60
    val duration =  if(hours>0) "${hours}h ${minutes}m" else "${minutes}m"
    txtRuntime.text = duration
}