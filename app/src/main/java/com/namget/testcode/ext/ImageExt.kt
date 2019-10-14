package com.namget.testcode.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.namget.testcode.R

/**
 * Created by Namget on 2019.10.14.
 */


fun ImageView.setImageWithGlide(url: String?) =
    url?.let {
        try {
            Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .into(this)
        } catch (ignore: Exception) {
        }
    }
        //error시 보여줄 화면들
        ?: setImageResource(0)


fun ImageView.setImageWithGlide(resId: Int) =
    resId?.let {
        try {
            Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .into(this)
        } catch (ignore: Exception) {
        }
    }