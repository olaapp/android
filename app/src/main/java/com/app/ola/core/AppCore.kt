package com.app.ola.core

//import com.uxcam.UXCam
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.google.firebase.analytics.FirebaseAnalytics
import com.app.ola.core.di.components.ApplicationComponent
import com.app.ola.core.di.components.DaggerApplicationComponent
import com.onesignal.OneSignal
import com.orm.SugarApp
import io.realm.Realm


/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */
class AppCore : SugarApp() {

    companion object {
        lateinit var instance: AppCore private set
        lateinit var applicationComponent: ApplicationComponent private set
        var mFirebaseAnalytics: FirebaseAnalytics? = null
        fun context(): Context = instance.applicationContext
        fun di(): ApplicationComponent = applicationComponent
        fun realm(): Realm = Realm.getDefaultInstance()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.builder().build()
        Realm.init(this)
        //UXCam.startWithKey("cql3mwlkylq92yg");
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "OPEN_APP")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "event")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}