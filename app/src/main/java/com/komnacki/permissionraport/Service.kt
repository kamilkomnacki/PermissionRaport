package com.komnacki.permissionraport

import io.reactivex.Observable
import retrofit2.http.POST

interface Service {
    @POST("helloWorld")
    fun helloWorld() : Observable<String>
}