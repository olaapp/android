package com.app.ola.environment.main


import android.content.Context
import android.graphics.Bitmap
//import android.support.v4.graphics.drawable.RoundedBitmapDrawable
//import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    /**
     * Gets timestamp in millis and converts it to HH:mm (e.g. 16:44).
     */
    fun formatTime(timeInMillis: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(timeInMillis)
    }

    /**
     * Crops image into a circle that fits within the ImageView.
     */
//    fun displayRoundImageFromUrl(context: Context, url: String?, imageView: ImageView) {
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .centerCrop()
//                .dontAnimate()
//                .into(object : BitmapImageViewTarget(imageView) {
//                    override fun setResource(resource: Bitmap?) {
//                        val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
//                        circularBitmapDrawable.isCircular = true
//                        imageView.setImageDrawable(circularBitmapDrawable)
//                    }
//                })
//    }
}