package com.app.ola.environment.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.ola.R
import com.app.ola.core.util.launchActivity
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        imageView4.setOnClickListener {
            launchActivity<HomeOla> {  }
        }
    }
}