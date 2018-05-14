package com.aliumujib.githubtrending.utils

import android.widget.ImageView

/**
 * Created by aliumujib on 12/05/2018.
 */

interface ImageLoader {
    fun load(url: String, imageView: ImageView, callback: (Boolean) -> Unit)
    fun load(url: String, imageView: ImageView, fadeEffect: Boolean = true)
}

