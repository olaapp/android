@file:Suppress("NON_EXHAUSTIVE_WHEN")

package com.app.ola.environment.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.ola.R
import com.app.ola.core.Core
import com.app.ola.core.util.*


import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.common.api.GoogleApiClient
//import com.google.android.gms.wearable.Node
//import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson



class MainActivity : AppCompatActivity(),
        DefaultFlow
//        GoogleApiClient.ConnectionCallbacks
{

    private var isRunning = true
    private lateinit var client: GoogleApiClient
    //private var connectedNodes: List<Node>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFlow()
    }

    override fun onStart() {
        super.onStart()
        isRunning = true
    }

    override fun onPause() {
        super.onPause()
        isRunning = false
    }

    override fun onResume() {
        super.onResume()
        isRunning = true
    }

    override fun onStop() {
        super.onStop()
        isRunning = false
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

//    override fun onConnected(bundle: Bundle?) {
//        Wearable.NodeApi.getConnectedNodes(client).setResultCallback {
//            connectedNodes = it.nodes
//        }
//    }
//
//    override fun onConnectionSuspended(code: Int) {
//        connectedNodes = null
//    }

    override fun initFlow() {
        val sfm = supportFragmentManager
        Core.listen(this) {
            if (isRunning) {
                executeOnUIThread {
                    when (it.case) {
                        Cases.FRAGMENT_CHANGE -> {
                            val currentFragment = sfm.findFragmentById(R.id.lytFragmentContainer)
                            val nextFragment = it.value as Fragment

                            if (currentFragment != null && !currentFragment::class.java.toString()
                                            .equals(nextFragment::class.java.toString())) {
                                if (sfm.backStackEntryCount > 3) {
                                    sfm.popBackStack()
                                }
                            }

                            sfm.beginTransaction()
                                    .replace(R.id.lytFragmentContainer, nextFragment)
                                    .addToBackStack(Cons.APP_NAME)
                                    .commitAllowingStateLoss()
                        }
                        Cases.FRAGMENT_BACK_REQUEST -> {
                            sfm.popBackStackImmediate()
                        }
                        Cases.MENU_MODULE_CHANGE -> {
                            navigationBar.menu.findItem(when (it.value as Int) {
                                0 -> R.id.btnGoals
                                1 -> R.id.btnProfile
                                else -> R.id.btnIdeas
                            }).isChecked = true
                        }
                    }
                }
            }
        }

        initNavigator()
        executeOnUIThread {
//            sfm.beginTransaction()
//                    .replace(R.id.lytFragmentContainer, GoalsFragment.newInstance())
//                    .commitAllowingStateLoss()
        }

//        client = GoogleApiClient.Builder(this)
//                .addApi(Wearable.API)
//                .addConnectionCallbacks(this)
//                .build()
//        client.connect()

        //reportSession()
    }

    fun initNavigator() {
//        navigationBar.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.btnGoals -> {
//                    Core.publish(Event(GoalsFragment.newInstance(), Cases.FRAGMENT_CHANGE))
//                    return@setOnNavigationItemSelectedListener true
//                }
//                R.id.btnProfile -> {
//                    Core.publish(Event(ProfileFragment.newInstance(), Cases.FRAGMENT_CHANGE))
//                    return@setOnNavigationItemSelectedListener true
//                }
//                else -> {
//                    Core.publish(Event(IdeasFragment.newInstance(), Cases.FRAGMENT_CHANGE))
//                    return@setOnNavigationItemSelectedListener true
//                }
//            }
//            false
//        }
    }



}
