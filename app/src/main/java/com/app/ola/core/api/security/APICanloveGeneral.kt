package com.app.ola.core.api.security

import com.app.ola.core.api.general_es.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APICanloveGeneral {
    //canlove
    @POST("getMyDogs")
    fun getMyDogs(@Body request: GeneralRequestModel): Call<List<MyDogsModel>>



    @POST("getUserInfo")
    fun getUserInfo(
            @Body request: AccountInfoRequestModel
    ): Call<List<UserInfo>>


    @POST("auth/register")
    fun createUserwfb(
            @Body request: SocialAuth
    ): Call<AuthResponse>

    @POST("auth/registerWithPhone")
    fun createUserWithPhone(
            @Body request: PhoneAuth
    ): Call<AuthResponse>


    @POST("auth/registerAnonymous")
    fun registerAnonymous(
            @Body request: AnonymousBiometric
    ): Call<AuthResponse>


    @POST("auth/registerBiometrics")
    fun registerBiometrics(
            @Body request: AnonymousBiometric
    ): Call<AuthResponse>


    @POST("auth/login")
    fun login(
            @Body request: RegisterMailPwd
    ): Call<AuthResponse>

//    @POST("createUserwfb")
//    fun createUserwfb(
//            @Body request: AccountCreationFBRequestModel
//    ): Call<UserInfoFB>


    @POST("getPlans")
    fun getPlans(@Body request: GeneralRequestModel): Call<List<CanlovePlansModel>>
    //UpdateCardRequestModel

    @POST("getPlansByMins")
    fun getPlansByMins(@Body request: UpdateCardRequestModel): Call<List<CanlovePlansModel>>

    @POST("createPet")
    fun createPet(@Body request: NewPetRequestModel): Call<NewPetResponseModel>


    @POST("updateDogPic")
    fun updatepicture(@Body request: UpdateProfileRequestModel): Call<UpdateProfileResponseModel>

    @POST("getPetDetails")
    fun getPetDetails(@Body request: GeneralRequestModel): Call<List<PetDetailsResponseModel>>

    @POST("updateDogDetails")
    fun updateDogDetails(@Body request: UpdateDogRequestModel): Call<UpdateDogResponseModel>

    @POST("deleteDog")
    fun deleteDog(@Body request: GeneralRequestModel): Call<DeleteDogResponse>


    @POST("updateUserProfile")
    fun updateUserProfile(@Body request: UpdateProfileRequestModel): Call<UpdateProfileResponseModel>


    @POST("updateUserName")
    fun updateUserName(@Body request: UpdateProfileRequestModel): Call<UpdateProfileResponseModel>

    @POST("addCardCanlove")
    fun addCardCanlove(@Body request: AddCardRequestModel): Call<AddCardResponseModel>

    @POST("getPM")
    fun getPM(@Body request: GeneralRequestModel): Call<List<PaymentsMethodsResponseModel>>

    @POST("updateDefaultPM")
    fun updateDefaultPM(@Body request: UpdateCardRequestModel): Call<UpdateCardRequestModel>

    @POST("addAddress")
    fun addAddress(@Body request: AddAddressRequestModel): Call<AddAddressResponseModel>

    @POST("addPhones")
    fun addPhones(@Body request: AddPhoneRequestModel): Call<AddPhoneResponseModel>

    @POST("updateDefaultAddress")
    fun updateDefaultAddress(@Body request: UpdateCardRequestModel): Call<UpdateCardRequestModel>

    @POST("getMyAddress")
    fun getMyAddress(@Body request: GeneralRequestModel): Call<List<MyAddressResponseModel>>


    @POST("getMyPhones")
    fun getMyPhones(@Body request: GeneralRequestModel): Call<List<AddPhonesResponseModel>>



    @POST("updateDefaultPhone")
    fun updateDefaultPhone(@Body request: UpdateCardRequestModel): Call<UpdateCardRequestModel>

    //default data
    @POST("getDefaulPhones")
    fun getDefaulPhones(@Body request: GeneralRequestModel): Call<List<DefaultPhonesResponseModel>>
    @POST("getDefaultCard")
    fun getDefaultCard(@Body request: GeneralRequestModel): Call<List<DefaultCardResponseModel>>
    @POST("getDefaultAddress")
    fun getDefaultAddress(@Body request: GeneralRequestModel): Call<List<DefaultAddressResponseModel>>


    //trips
    @POST("newTrip")
    fun newTrip(@Body request: NewTripRequestModel): Call<NewTripResponseModel>

    @POST("addDogsToTrip")
    fun addDogsToTrip(@Body request: AddDogsRequestModel): Call<AddDogsResponseModel>


    //notifications
    @POST("updPush")
    fun updadtePushNotifications(@Body request: UpdateCardRequestModel): Call<UpdateCardRequestModel>


    @POST("getTripDates")
    fun getTripDates(@Body request: GeneralRequestModel): Call<List<TripScheduleDetailModel>>


    @POST("getMyActiveTrips")
    fun getMyActiveTrips(@Body request: GeneralRequestModel): Call<List<ActiveTripsModel>>

    @POST("getMyHistoryTrips")
    fun getMyHistoryTrips(@Body request: GeneralRequestModel): Call<List<ActiveTripsModel>>

    @POST("getCanloverData")
    fun getCanloverData(@Body request: GeneralRequestModel): Call<List<CanloverAssignedDataModel>>


    @POST("getTripDetails")
    fun getTripDetails(@Body request: GeneralRequestModel): Call<List<CanlovePlansModel>>


    @POST("getTripDetailsFull")
    fun getTripDetailsFull(@Body request: GeneralRequestModel): Call<List<TripDetailFullModel>>

    @POST("getDogsByTrip")
    fun getDogsByTrip(@Body request: GeneralRequestModel): Call<List<DogsInTripModel>>



    @POST("getTripByDogs")
    fun getTripByDogs(@Body request: GeneralRequestModel): Call<List<TripsOfADogModel>>


    @POST("updateUserBirth")
    fun updateUserBirth(@Body request: UpdateCardRequestModel): Call<UpdateCardRequestModel>


    @POST("getMainTripFromChild")
    fun getMainTripFromChild(@Body request: GeneralRequestModel): Call<List<MainTripFromChildModel>>


    @POST("auth/prevalidate")
    fun prevalidateAPI(
            @Body request: PrevalidateRequest
    ): Call<PrevalidateResponse>
}