package com.app.ola.core.di.components

import com.app.ola.core.di.modules.NetworkingServicesModule
import com.app.ola.core.di.modules.ViewModelModule
import com.app.ola.core.di.scopes.AppScope
import com.app.ola.core.util.MyFirebaseMessagingService
import com.app.ola.environment.main.aes.*


import com.app.ola.environment.session.splash.InitialActivity
import dagger.Component

/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */
@AppScope
@Component(modules = [NetworkingServicesModule::class, ViewModelModule::class])
interface ApplicationComponent {




    fun inject(registerActivity: MyFirebaseMessagingService)


    fun inject(initialActivity: InitialActivity)


}