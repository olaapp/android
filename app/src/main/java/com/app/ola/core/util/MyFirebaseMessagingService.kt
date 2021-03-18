package com.app.ola.core.util


import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.app.ola.R
import com.app.ola.core.Core
import com.app.ola.environment.main.HomeActivity
import com.app.ola.environment.main.HomeOla
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.sendbird.android.SendBird
import com.sendbird.android.SendBird.PushTokenRegistrationStatus
import com.sendbird.android.constant.StringSet


//import com.app.ola.environment.main.aes.HomeActivity


class MyFirebaseMessagingService : FirebaseMessagingService() {

    //    @Inject
//    lateinit var apiUsersModule: APIUsersModule
    private var notificationManager: NotificationManager? = null

    private val CHANNEL_ID = "com.app.ola"

    override fun onCreate() {
        super.onCreate()
        Core.DI.inject(this)
    }

    override fun onNewToken(tokenx: String) {
        setPreference("tok",tokenx)
// Register a registration token to Sendbird server.
        // Register a registration token to Sendbird server.
//        SendBird.registerPushTokenForCurrentUser(StringSet.token) { ptrs, e ->
//            if (e != null) {
//                // Handle error.
//            }
//            if (ptrs == PushTokenRegistrationStatus.PENDING) {
//                // A token registration is pending.
//                // Retry the registration after a connection has been successfully established.
//            }
//        }
    }
    fun isForeground(myPackage: String): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfo = manager.getRunningTasks(1)
        val componentInfo = runningTaskInfo[0].topActivity
        return componentInfo!!.packageName == myPackage
    }
    override fun onMessageReceived(rm: RemoteMessage) {
        Log.i("1RM:",rm.data.toString())
//        Log.i("2RM:", rm.notification.toString())
//        Log.i("3RM:", rm.notification!!.title.toString())
//        Log.i("4RM:", rm.notification!!.body.toString())
        if(isForeground("com.app.ola")){
            Log.d("ESTADO", "abierta")
            sendMessage(rm.data["message"]!!, rm.data["message"]!!)
        }
//        else{
//            setPreference("pn_title", rm.data["title"]!!)
//            setPreference("pn_body", rm.data["alert"]!!)
//            setPreference("pn_status", "rec")
//        }

//        val trip_id = (rm.data["trip_id"])
//        val canlover_id= (rm.data["canlover_id"])
//        val titlex= (rm.data["title"])
//        val bodyx= (rm.data["body"])
        //sendNotification(rm.data, rm.notification?.title, rm.notification?.body, trip_id!!, canlover_id!!, titlex!!,bodyx!!)
        var mx = rm.data["message"]
        Log.e("MX RM:",mx)
        sendNotification(rm.data, rm.data["message"], rm.data["message"], "", "", rm.data["message"]!!, rm.data["message"]!!)

    }

    private fun sendMessage(t: String, m: String) {
        val intent = Intent("my-event")
        intent.putExtra("message", m)
        intent.putExtra("title", t)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun setupChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val adminChannelName = "CANLOVECHANNEL"
            val adminChannelDescription = "Default channel for notifications"

            val adminChannel: NotificationChannel
            adminChannel = NotificationChannel(CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH)
            adminChannel.description = adminChannelDescription
            adminChannel.enableLights(true)
            adminChannel.lightColor = Color.GREEN
            adminChannel.enableVibration(true)
            if (notificationManager != null) {
                notificationManager?.createNotificationChannel(adminChannel)
            }
        }
    }

    private fun sendNotification(payload: Map<String, String>?, pushTitle: String?, pushBody: String?, tripid: String, canloverid: String, titlex: String, bodyx: String) {

        Log.e("SENDNOTIFICATION",payload.toString())

        var title = pushTitle ?: titlex
        var body = pushBody ?: bodyx
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

//        if (payload != null) {
//            for (entry in payload) {
//                when (entry.key) {
//                    "title" -> title = entry.value
//                    "body" -> body = entry.value
//                }
//            }
//        }

        val notifyIntent: Intent = Intent(this, HomeOla::class.java)

        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pendingIntent = PendingIntent.getActivity(Core.APP_CONTEXT, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val icon = BitmapFactory.decodeResource(resources, R.drawable.ic_logo_aes_circle)

        setupChannels()

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_filled_ola)
                .setLargeIcon(icon)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        notificationManager?.notify(3301, notificationBuilder.build())
    }
}