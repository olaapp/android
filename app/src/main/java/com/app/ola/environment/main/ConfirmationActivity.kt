package com.app.ola.environment.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.ola.R
import com.app.ola.core.util.launchActivity
import com.app.ola.core.util.log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.concurrent.schedule

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)


        Timer("SettingUp", false).schedule(2000) {
            launchActivity<HomeOla>(true) {  }
        }

    }
}