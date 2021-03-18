package com.app.ola.core.api.users

import com.app.ola.core.api.general.GeneralResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface APIUsersModule {

    @POST("createUserwemail")
    fun createUser(@Body request: CreateUserRequestModel): Call<CreateUserResponseModel>

    @PUT("users/update-picture")
    fun updateUserPicture(@Body request: UpdatePictureRequestModel): Call<GeneralResponseModel>

    @PUT("users/update-token")
    fun updateToken(@Body request: UpdateTokenRequestModel): Call<GeneralResponseModel>

    @PUT("users/update-notification")
    fun updateNotifications(@Body request: UpdateNotificationsRequestModel): Call<GeneralResponseModel>

    @POST("users/reset-password")
    fun resetPassword(@Body request: ResetPasswordRequestModel): Call<GeneralResponseModel>
}