package com.app.ola.core.di.modules

import com.app.ola.core.di.scopes.AppScope
import com.app.ola.core.util.Cons
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */
@Module
class NetworkingModule {

    @Provides
    @AppScope
    @Named("logging_interceptor")
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @AppScope
    @Named("error_interceptor")
    fun provideErrorInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
            val response = it.proceed(request)

            if(response.code() > 399){
                throw IOException("Server error code ${response.code()}")
            }

            response
        }
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(@Named("logging_interceptor") loggingInterceptor: Interceptor,
                            @Named("error_interceptor") errorInterceptor: Interceptor): OkHttpClient{
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(errorInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
                .baseUrl(Cons.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }
}