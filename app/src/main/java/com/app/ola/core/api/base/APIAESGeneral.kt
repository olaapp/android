package com.app.ola.core.api.base

import com.app.ola.core.api.general_es.*
import com.app.ola.core.api.security.UpdateProfileRequestModel
import com.app.ola.core.api.security.UpdateProfileResponseModel
import com.app.ola.core.util.Cons.URL_AES_CORE
import retrofit2.Call
import retrofit2.http.*


interface APIAESGeneral {


    @POST("updateUserProfile")
    fun updateUserProfile(@Body request: UpdateProfileRequestModel): Call<UpdateProfileResponseModel>

    @POST("/user_profile/push_token")
    fun setToken(@Header("Authorization") authorization: String,@Body request: UpdateTokenModel):
            Call<UpdateTokenResponse>

    @GET("places/read")
    fun getPlaces(): Call<PlacesModel>

    @GET("places/read")
    fun getPlacesByLocation(
            @Query("lt") lt: String,
            @Query("ln") lng: String
    ): Call<PlacesModel>

    @GET("departments/read")
    fun getDepartments(): Call<DepartmentsModel>

    @GET("cities/read")
    fun getCitiesByDepartment(
            @Query("department_id") id: String
    ): Call<CitiesModel>

    @GET("places/read")
    fun getPlacesFiltered(
            @Query("department_id") depto_id: String,
            @Query("city_id") city_id: String
    ): Call<PlacesModel>

    @POST("theft_report/create")
    fun createTheft(@Header("Authorization") authorization: String,@Body request: CreateTheftModel):
            Call<CreateTheftResponseModel>

    @POST("images/create")
    fun uploadImageToTheft(@Header("Authorization") authorization: String,@Body request: UploadImageRequestModel):
            Call<UploadImageResponseModel>

    @GET("theft_report/read")
    fun getMyTheftReportsEP(@Header("Authorization") authorization: String):
            Call<MyTheftReportsModel>

    @GET("aes_news/read")
    fun getNewsEP(@Header("Authorization") authorization: String):
            Call<NewsModel>

    @GET("power_outgages/read")
    fun getPowerOutgagesEP(@Header("Authorization") authorization: String):
            Call<PowerOutgagesModel>

    @POST("power_outgages/findByNIC")
    fun findPOByNIC(@Header("Authorization") authorization: String,@Body request: FindPOByNICRequestModel):
            Call<PowerOutgagesModel>

    @GET("hazard_report/read")
    fun getMyHazardReportsEP(@Header("Authorization") authorization: String):
            Call<MyHazardReportsModel>

    @POST("hazard_report/create")
    fun createHazard(@Header("Authorization") authorization: String,@Body request: HazardReportRequestModel):
            Call<HazardReportResponseModel>

    @GET("user_profile/read")
    fun getUserInformation(@Header("Authorization") authorization: String):
            Call<MyUserModel>


    @POST("user_profile/addNIC")
    fun addNICEP(@Header("Authorization") authorization: String,@Body request: AddNICSModel):
            Call<AddNICResponseModel>

    @POST("user_profile/update")
    fun updateUserData(@Header("Authorization") authorization: String,
                       @Body request: UpdateMyDataUserRequestModel):
            Call<UpdateMyDataUserResponseModel>

    @GET("user_profile/readNICS")
    fun getMyNICSEP(@Header("Authorization") authorization: String):
            Call<MyNicsDataModel>

    @GET("notif_history/read")
    fun getMyNotificatiosEP(@Header("Authorization") authorization: String):
            Call<MyNotificationsModel>


    @GET("innovation_forms/read")
    fun getInnovationForms(@Header("Authorization") authorization: String):
            Call<InnovationFormsListModel>

    //innovationForms
    @POST("innovation_forms/create")
    fun createInnoForm(@Header("Authorization") authorization: String,@Body request: CreateInnovationRequestModel):
            Call<CreateTheftResponseModel>


    @POST(URL_AES_CORE+"GetLecturaAnterior")
    fun getConsumoOnline(
            @Body request: GetLecturaAnteriorModel):
            Call<GetLecturaAnteriorResponseModel>

    @GET
    fun getLastBill(
            @Url url: String):
            Call<getNICBillStatusModelResponse>

    @GET
    fun getPMAES(
            @Url url: String):
            Call<PaymentsMethodsAESMethodModel>

    @POST(URL_AES_CORE+"SetProcesoPago")
    fun setProcPago(
            @Body request: SetProcesoPagoRequestModel):
            Call<PaymentProcessModel>


    @GET
    fun getHistory(
            @Url url: String):
            Call<BillHistoryListModel>

    @GET("appointments/read")
    fun getPlacesAppointments(): Call<PlacesModel>

    @GET("appointments/transactions/read")
    fun getTrxAppointments(): Call<AppointmentsTrxModel>

    @GET("appointments/read")
    fun getDatesByUNICOM(
            @Query("place_id") lt: String
    ): Call<DatesByUNICOM>

    @GET("appointments/read")
    fun getTimesByDate(
            @Query("place_id") place_id: String,
            @Query("day") dy: String,
            @Query("month") mt: String,
            @Query("year") yr: String
    ): Call<TimesByDateModel>


    @POST("appointments/create")
    fun createAppointmentx(@Header("Authorization") authorization: String,@Body request: CreateAppointmentRequestModel):
            Call<NewAppointmentResponseModel>

    @GET("cons_simulator/read")
    fun getAppliances(@Header("Authorization") authorization: String):
            Call<AppliancesModel>



    @GET("appointments/readAppointments")
    fun getMyAppointments(@Header("Authorization") authorization: String):
            Call<MyAppointmentsModel>


    @GET("new_service/read")
    fun getMyNSList(@Header("Authorization") authorization: String):
            Call<myNewRequestsModel>

    @GET("places/read")
    fun singlePlaceByID(
            @Query("id") lt: String
    ): Call<PlacesModel>


    @GET("user_settings/read")
    fun getMySettings(@Header("Authorization") authorization: String):
            Call<MySettingsModelRead>

    @POST("user_settings/update")
    fun updatePref(@Header("Authorization") authorization: String,@Body request: UpdatePrefModel):
            Call<UpdatePrefResponseModel>

    @GET("account_operations/read")
    fun getMyOperationsM(@Header("Authorization") authorization: String):
            Call<MyOperationsModel>


    @GET
    fun getType1(
            @Url url: String):
            Call<OperationsModel>

    @POST("account_operations/create/1")
    fun newOperationType1(@Header("Authorization") authorization: String,@Body request: OperationsRequest1Model):
            Call<OperationsResponse1Model>

    @POST("account_operations/create/2")
    fun newOperationType2(@Header("Authorization") authorization: String,@Body request: OperationsRequest1Model):
            Call<OperationsResponse2Model>

    @POST("account_operations/create/3")
    fun newOperationType3(@Header("Authorization") authorization: String,@Body request: OperationsRequest1Model):
            Call<OperationsResponse3Model>

    @POST("account_operations/create/4")
    fun newOperationType4(@Header("Authorization") authorization: String,@Body request: OperationsRequest2Model):
            Call<OperationResponse4Model>

    @POST("account_operations/create/5")
    fun newOperationType5(@Header("Authorization") authorization: String,@Body request: OperationsRequest2Model):
            Call<OperationResponse4Model>

    @POST("account_operations/create/6")
    fun newOperationType6(@Header("Authorization") authorization: String,@Body request: OperationsRequest1Model):
            Call<OperationsResponse3Model>

    @POST("account_operations/create/7")
    fun newOperationType7(@Header("Authorization") authorization: String,@Body request: OperationsRequest3Model):
            Call<OperationsResponse3Model>

    @POST("new_service/create")
    fun nsrequest(@Header("Authorization") authorization: String,@Body request: NewServicesRequestModel):
            Call<NewNSResponseModel>

    @GET("new_service/read_dt")
    fun getNSDetail(
            @Header("Authorization") authorization:String,
            @Query("id") lt: String
    ): Call<NewServiceDetailModel>

    @POST("ns/update")
    fun nsrequestUpdateField(@Header("Authorization") authorization: String,@Body request: NewServicesUpdateFieldRequestModel):
            Call<NewNSUpdateResponseModel>


    @GET("theft_report/read")
    fun getTheftCODE(
            @Header("Authorization") authorization:String,
            @Query("id") lt: String
    ): Call<TheftDetailsDBModel>

    @GET("hazard_report/read")
    fun getHazardCODE(
            @Header("Authorization") authorization:String,
            @Query("id") lt: String
    ): Call<HazardDetailsDBModel>

    @POST("user_profile/removeNIC")
    fun deleteNICFromAccount(@Header("Authorization") authorization: String,@Body request: DeleteNICRequestModel):
            Call<DeleteNICModel>


    @POST("new_service/delete")
    fun deleteNS(@Header("Authorization") authorization: String,@Body request: DeleteNSRequestModel):
            Call<DeleteNSRequestModel>
}
