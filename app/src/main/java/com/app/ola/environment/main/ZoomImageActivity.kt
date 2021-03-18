package com.app.ola.environment.main

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.app.ola.R
import com.app.ola.core.util.getStringPreference
import com.app.ola.core.util.toastyInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_zoom_image.*
import kotlinx.android.synthetic.main.row_doctors.view.*


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class ZoomImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_zoom_image)

        val url1 = getStringPreference("zoomImage")
        val url2 = getStringPreference("zoomVideo")

        Log.e("IMAGE:", url1)
        Log.e("video:", url2)

        if(url1.length>0){
            zooomImagex.visibility= View.VISIBLE
           // video.visibility=View.GONE
            Glide.with(this).load(url1).into(zooomImagex)
        }
        else{
            playVideo(url2)
        }


    }

    fun playVideo(url: String){


        val LINK = url

        val videoView = findViewById<View>(R.id.video) as VideoView
        val mc = MediaController(this)
        mc.setAnchorView(videoView)
        mc.setMediaPlayer(videoView)
        val video = Uri.parse(LINK)
        videoView.setMediaController(mc)
        videoView.setVideoURI(video)
        videoView.start()


    }


}