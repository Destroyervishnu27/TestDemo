package com.testapp.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.testapp.R

class DataBindingAdapter {

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageResourceUrl(
            imageView: ImageView,
            url: String?
        ) {
            if (url != null) Glide.with(imageView.context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}

