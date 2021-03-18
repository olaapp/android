package com.app.ola.core.util

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Build
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.gson.Gson
import com.app.ola.R
import com.app.ola.core.Core
import es.dmoral.toasty.Toasty
import io.realm.Realm
import io.realm.RealmModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */


//Log anything anywhere
fun Context.log(message: String) {
    Log.i(Cons.APP_NAME, message)
}

//Direct getText
fun TextView.getString(): String {
    return text.toString()
}

//EditText isEmpty
fun EditText.isEmpty(): Boolean {
    return text.isEmpty()
}
fun Spinner.populate(entries: ArrayList<String>) {
    val spinnerArray = entries.toArray(arrayOfNulls<String>(0))
    val spinnerAdapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, spinnerArray)
    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    adapter = spinnerAdapter
}

fun Spinner.onItemSelected(action: (position: Int) -> Unit = {}){
    onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            action.invoke(position)
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}

fun MaterialAutoCompleteTextView.onItemSelected(action: (position: Int) -> Unit = {}){
    onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            action.invoke(position)
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}
//View Visibility
fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun Context.getResourcesColor(id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun TextView.setTextColorFromRes(id: Int) {
    setTextColor(context.getResourcesColor(id))
}

fun View.fadeInAnimate(){
    visibility = View.VISIBLE
    alpha = 0f
    scaleX = 0f
    scaleY = 0f
    animate()
            .alpha(1f)
            .scaleX(1f).scaleY(1f)
            .setDuration(300)
            .start()
}
fun View.fadeOutAnimate(){
    alpha = 0f
    scaleX = 0f
    scaleY = 0f
    animate()
            .alpha(1f)
            .scaleX(1f).scaleY(1f)
            .setDuration(300)
            .start()
    visibility = View.GONE
}

//Activity StatusBarColor
fun AppCompatActivity.configureStatusBarColor(color: Int = R.color.colorPrimary, darkIcons: Boolean = false) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.statusBarColor = ContextCompat.getColor(this, color)
        var flags = window.decorView.systemUiVisibility
        flags = if (darkIcons) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        window.decorView.systemUiVisibility = flags
    }
}

//Inline function for starting an Activity
inline fun <reified T : AppCompatActivity> AppCompatActivity.launchActivity(
        closeCurrent: Boolean = false,
        noinline init: Intent.() -> Unit = {}) {
    val i = Intent(this, T::class.java)
    i.init()
    startActivity(i)
    if (closeCurrent) {
        finish()
    }
}

inline fun <reified T : FragmentActivity> FragmentActivity.launchActivity(
        closeCurrent: Boolean = false,
        noinline init: Intent.() -> Unit = {}) {
    val i = Intent(this, T::class.java)
    i.init()
    startActivity(i)
    if (closeCurrent) {
        finish()
    }
}

fun Fragment.executeIfSafe(action: () -> Unit = {}) {
    if (!(isRemoving || activity == null || isDetached || !isAdded || view == null)) {
        action.invoke()
    }
}

//Configure simple vertical or horizontal RecyclerView
fun <T> RecyclerView.configureRecycler(adapter: DynamicAdapter<T>, isVertical: Boolean = true) {
    itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context,
            if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL, false)
    this.adapter = adapter
}

//Simple internet verifier
@SuppressLint("MissingPermission")
fun Context.isInternetConnectionAvailable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val ni = cm.activeNetworkInfo
    if (ni != null && ni.isConnected) {
        return true
    }

    return false
}

/**
 * Shared Preferences Handling
 */
fun Context.sharedPreferencesInstance(): SharedPreferences {
    return getSharedPreferences(Cons.APP_NAME, Context.MODE_PRIVATE)
}

fun Context.setPreference(key: String, value: Any) {
    val editor = sharedPreferencesInstance().edit()

    when (value) {
        is String -> editor.putString(key, value)
        is Int -> editor.putInt(key, value)
        is Boolean -> editor.putBoolean(key, value)
        is Float -> editor.putFloat(key, value)
        is Long -> editor.putLong(key, value)
        else -> editor.putString(key, value.toString())
    }

    editor.apply()
    editor.commit()
    log("setPreference | $key = $value")
}

fun Context.getStringPreference(key: String): String {
    return sharedPreferencesInstance().getString(key, "")!!
}

fun Context.getIntegerPreference(key: String): Int {
    return sharedPreferencesInstance().getInt(key, 0)
}

fun Context.getBooleanPreference(key: String): Boolean {
    return sharedPreferencesInstance().getBoolean(key, false)
}

fun Context.getFloatPreference(key: String): Float {
    return sharedPreferencesInstance().getFloat(key, 0.0f)
}

fun Context.getLongPreference(key: String): Long {
    return sharedPreferencesInstance().getLong(key, 0L)
}
fun <T> Context.setListToPreferences(key:String, list:List<T>) {
    val editor = sharedPreferencesInstance().edit()
    val gson = Gson()
    val json = gson.toJson(list)
    editor.putString(key, json)
    editor.apply()
}

fun Context.showToast(message: String, isLong: Boolean = false) {
    Toast.makeText(this, message,
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.onRequestFailure(action: () -> Unit = {}) {
    if (!isInternetConnectionAvailable()) {
        toastyWarning(getString(R.string.internet_connection_error), true)
    } else {
        action.invoke()
    }
}

fun Context.clearPreferences() {
    val editor = sharedPreferencesInstance().edit()
    editor.clear()
    editor.apply()
    log("Preferences cleared.")
}

//REALM SHORTCUTS

fun <T : RealmModel> Class<T>.newID(): Int {
    val maxID: Number? = Core.REALM.where(this).max("id")
    return if (maxID != null) maxID.toInt() + 1 else 1
}

fun Realm.doTransaction(transaction: Realm.() -> Unit = {}) {
    try {
        this.beginTransaction()
        this.transaction()
    } catch (e: Exception) {
        Core.APP_CONTEXT.log("RealmTransactionError: " + e.message)
        this.cancelTransaction()
    } finally {
        this.commitTransaction()
    }
}

//RETROFIT SHORCUTS

suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    continuation.resume(response.body()!!)
                } else {
                    continuation.resumeWithException(
                            Throwable("Request error \nStatus code: ${response.code()} \nStatus body: ${response.errorBody()}")
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}

//TOASTY

fun Context.toastySuccess(message: String = "", isLong: Boolean = false) {
    val tp: Typeface
    tp= ResourcesCompat.getFont(this, R.font.montserrat)!!
    Toasty.Config.getInstance()
            .setToastTypeface(tp)
            .apply();
    Toasty.success(this, message,
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.toastyError(message: String = "", isLong: Boolean = false) {
    val tp: Typeface
    tp= ResourcesCompat.getFont(this, R.font.montserrat)!!
    Toasty.Config.getInstance()
            .setToastTypeface(tp)
            .apply();
    Toasty.error(this, message,
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.toastyWarning(message: String = "", isLong: Boolean = false) {
    val tp: Typeface
    tp= ResourcesCompat.getFont(this, R.font.montserrat)!!
    Toasty.Config.getInstance()
            .setToastTypeface(tp)
            .apply();
    Toasty.warning(this, message,
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}

fun Context.toastyInfo(message: String = "", isLong: Boolean = false) {
    val tp: Typeface
    tp= ResourcesCompat.getFont(this, R.font.montserrat)!!
    Toasty.Config.getInstance()
            .setToastTypeface(tp)
            .apply();
    Toasty.info(this, message,
            if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}


//Dagger n ViewModel
inline fun <reified T : ViewModel> FragmentActivity.initViewModel(
        viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders
            .of(this, viewModelFactory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.initViewModel(
        viewModelFactory: ViewModelProvider.Factory): T {
    return ViewModelProviders
            .of(this, viewModelFactory)[T::class.java]
}


fun View.hideKeyboard() {
    val imm = Core.APP_CONTEXT.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(rootView.windowToken, 0)
}

fun Activity.executeOnUIThread(action: () -> Unit) {
    runOnUiThread {
        Runnable {
            action.invoke()
        }.run()
    }
}

fun Fragment.executeOnUIThread(action: () -> Unit) {
    activity?.executeOnUIThread {
        action.invoke()
    }
}

//Lifecycle and Coroutines

fun ViewModel.launchAPIRequest(action: suspend () -> Unit) {
    viewModelScope.launch {
        withContext(Dispatchers.IO) {
            action.invoke()
        }
    }

}