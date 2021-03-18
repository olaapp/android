package com.app.ola.environment.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.ola.R
import com.app.ola.core.util.DefaultFlow
import com.app.ola.core.util.configureStatusBarColor
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeOla : AppCompatActivity(), DefaultFlow {
    override fun initFlow() {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_textil)
        configureStatusBarColor(R.color.white)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        initFlow()
    }
}