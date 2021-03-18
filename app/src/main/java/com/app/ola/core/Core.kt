package com.app.ola.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.app.ola.core.util.Event

/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */
object Core {

    /** LiveData EventBus */
    private val EVENT_BUS by lazy {
        MutableLiveData<Event<Any>>()
    }
    /** Application Context */
    val APP_CONTEXT by lazy {
        AppCore.context()
    }
    val DI by lazy {
        AppCore.di()
    }
    val REALM by lazy {
        AppCore.realm()
    }

    //#####################################################################
    //          LiveData EventBus
    //#####################################################################

    fun publish(event: Event<Any>) {
        EVENT_BUS.postValue(event)
    }

    fun listen(owner: LifecycleOwner, action: (Event<*>) -> Unit){
        EVENT_BUS.observe(owner, Observer {
            action.invoke(it)
        })
    }
}