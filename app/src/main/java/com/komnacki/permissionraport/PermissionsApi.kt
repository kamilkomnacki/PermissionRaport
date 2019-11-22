package com.komnacki.permissionraport

import io.reactivex.Observable
import retrofit2.http.GET

interface PermissionsApi {
    @GET("/app/api/get/wapnpoland@gmail.com/permission/CONTACTS")
    fun getContacts(/*@Path("email") email : String*/) : Observable<Contacts>
}