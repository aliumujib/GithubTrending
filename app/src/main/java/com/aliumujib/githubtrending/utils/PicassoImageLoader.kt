package com.aliumujib.githubtrending.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
* Created by aliumujib on 12/05/2018.
*/

class PicassoImageLoader(private val picasso: Picasso) : ImageLoader {

    override fun load(url: String, imageView: ImageView, callback: (Boolean) -> Unit) {
        picasso.load(url).into(imageView, FetchCallback(callback))
    }

    override fun load(url: String, imageView: ImageView, fadeEffect: Boolean) {
        if (fadeEffect)
            picasso.load(url).into(imageView)
        else
            picasso.load(url).noFade().into(imageView)
    }

    private class FetchCallback(val delegate: (Boolean) -> Unit): Callback {
        override fun onError(e: Exception?) {
            delegate(false)
        }

        override fun onSuccess() {
            delegate(true)
        }
    }
}