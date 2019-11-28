package com.komnacki.permissionraport

import com.komnacki.permissionraport.easy_device_info.baterry.EasyBatteryPOJO
import com.komnacki.permissionraport.easy_device_info.sensor.EasySensorsPOJO
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PermissionsApi {
    //    EasyDeviceInfo
    @POST("/app/api/add/collection/{email}/permission/CONNECTED_EMAILS")
    fun sendConnectedEmails(@Path("email") email : String, @Body easyIdsPOJO : EasyIdsPOJO) : Observable<ApiResponse>

    @POST("/app/api/add/collection/{email}/permission/SENSORS")
    fun sendSensorsInfo(email : String, @Body sensorsInfo : EasySensorsPOJO) : Observable<ApiResponse>

    @POST("/app/api/add/collection/{email}/permission/BATTERY_STATE")
    fun sendBatteryState(@Path("email") email : String, @Body easyBatteryPOJO : EasyBatteryPOJO) : Observable<ApiResponse>

    @GET("/app/api/get/{email}/permission/CONTACTS")
    fun getContacts(@Path("email") email : String) : Observable<ContactsPOJO>

    @POST("/app/api/add/collection/{email}/permission/CONTACTS")
    fun sendContacts(@Path("email") email : String, @Body contactsPOJO : ContactsPOJO) : Observable<ApiResponse>

    @GET("/app/api/send_email/user/{email}")
    fun sendRaport(@Path("email") email : String) : Observable<ApiResponse>


}