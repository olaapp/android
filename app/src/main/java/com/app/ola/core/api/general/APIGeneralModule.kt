package com.app.ola.core.api.general

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIGeneralModule {

    @GET("info")
    fun getKnowGodInfo(): Call<GetKnowGodInfoResponseModel>

    @GET("ideas")
    fun getIdeas(
          @Query("type") type: String
    ): Call<GetIdeasResponseModel>

}