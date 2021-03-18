@file:Suppress("UNCHECKED_CAST")

package com.app.ola.core.di.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.ola.core.di.scopes.AppScope
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@AppScope
class ViewModelFactory @Inject constructor(
        private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T

}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)