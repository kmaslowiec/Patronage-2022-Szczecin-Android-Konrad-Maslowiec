package com.example.android.intivetaskone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.android.intivetaskone.network.InfoProperty

class RecyclerViewAdapter(val infoList: List<InfoProperty>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(infoList[position])
    }

    override fun getItemCount(): Int {
        return infoList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(info: InfoProperty) {
            val title = itemView.findViewById<TextView>(R.id.text_title)
            val desc = itemView.findViewById<TextView>(R.id.text_desc)
            val url = itemView.findViewById<ImageView>(R.id.image_info)

            title.text = info.title
            desc.text = info.desc
            bindImage(url, info.url)
        }

        private fun bindImage(imgView: ImageView, imgUrl: String?) {
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
    }
}
