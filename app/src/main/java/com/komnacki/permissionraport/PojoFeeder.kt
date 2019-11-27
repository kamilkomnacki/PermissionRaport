package com.komnacki.permissionraport

import io.reactivex.Observable

interface PojoFeeder {
    fun getPOJO() : Observable<out POJO>
    fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse>
}