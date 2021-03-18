package com.app.ola.environment.main.aes

//import com.crashlytics.android.Crashlytics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
//import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.app.ola.core.api.general_es.*
import com.app.ola.core.api.base.APIAESGeneral
//import com.app.ola.core.api.textil.Repairs
import com.app.ola.core.util.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


class AESViewModel @Inject constructor(
        private val api: APIAESGeneral
) : ViewModel() {

    private val placesLiveData = MutableLiveData<PlacesModel>()
    fun placesResult(): LiveData<PlacesModel> = placesLiveData

    fun getPlacesList(){
        GlobalScope.launch {
            try {
                val response = api.getPlaces().await()
                placesLiveData.postValue(response)
            }catch (e: Throwable){
                placesLiveData.postValue(PlacesModel(
                        emptyList(),e.localizedMessage,"FAIL"
                ))
                Log.d("ERROR",e.localizedMessage.toString())
                //FirebaseCrashlytics.getInstance().recordException(e)
                //Crashlytics.logException(e)
                //profilePicLiveData.postValue(GetStreaksResponseModel(Core.APP_CONTEXT.getStringPreference(Cons.USER_UUID)))
            }
        }
    }

}