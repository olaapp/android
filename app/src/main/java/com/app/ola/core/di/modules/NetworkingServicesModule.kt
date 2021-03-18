package com.app.ola.core.di.modules

import com.app.ola.core.api.security.APICanloveGeneral
import com.app.ola.core.api.general.APIGeneralModule
import com.app.ola.core.api.base.APIAESGeneral


import com.app.ola.core.api.users.APIUsersModule
import com.app.ola.core.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */
@Module(includes = [NetworkingModule::class])
class NetworkingServicesModule {

    @Provides
    @AppScope
    fun provideAPIUsersModule(retrofit: Retrofit): APIUsersModule{
        return retrofit.create(APIUsersModule::class.java)
    }



    @Provides
    @AppScope
    fun provideAPIAESGeneralModule(retrofit: Retrofit): APIAESGeneral{
        return retrofit.create(APIAESGeneral::class.java)
    }

    @Provides
    @AppScope
    fun provideAPIGeneralModule(retrofit: Retrofit): APIGeneralModule{
        return retrofit.create(APIGeneralModule::class.java)
    }


    @Provides
    @AppScope
    fun provideAPICanloveGeneral(retrofit: Retrofit): APICanloveGeneral{
        return retrofit.create(APICanloveGeneral::class.java)
    }



}