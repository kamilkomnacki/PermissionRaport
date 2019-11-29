package com.komnacki.permissionraport

import io.reactivex.Observable

interface PojoFeeder {
    fun getPOJO() : POJO
    fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse>
}