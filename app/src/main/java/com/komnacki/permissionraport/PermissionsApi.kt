package com.komnacki.permissionraport

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PermissionsApi {
    @GET("/app/api/get/{email}/permission/CONTACTS")
    fun getContacts(@Path("email") email : String) : Observable<ContactsPOJO>

    @POST("/app/api/add/collection/{email}/permission/CONTACTS")
    fun sendContacts(@Path("email") email : String, @Body contactsPOJO : ContactsPOJO) : Observable<ApiResponse>

    @GET("/app/api/send_email/user/{email}")
    fun sendRaport(@Path("email") email : String) : Observable<ApiResponse>
}