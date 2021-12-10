package com.example.android.intivetaskone

import android.webkit.WebSettings
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build().toString()
        val userAgentHeader =
            LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(imgView.context))
                .build()
        val glideUrl = GlideUrl(imgUri, userAgentHeader)


        Glide.with(imgView.context).load(glideUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            ).load(glideUrl)
            .into(imgView)
    }
}