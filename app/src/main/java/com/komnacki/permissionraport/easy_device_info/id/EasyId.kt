package com.komnacki.permissionraport.easy_device_info.id

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.EasyIdsPOJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyIdMod
import io.reactivex.Observable

class EasyIds : PojoFeeder {
    constructor(context : Context) {
        this.easyIdMod = EasyIdMod(context)
    }

    var easyIdMod : EasyIdMod? = null

    override fun getPOJO() : Observable<EasyIdsPOJO> {
        return Observable.just(getUserEmails())
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendConnectedEmails(email, getUserEmails())
    }

    private fun getUserEmails() : EasyIdsPOJO {
        val emailIds = easyIdMod?.accounts
        return if (emailIds != null && emailIds.isNotEmpty()) {
            EasyIdsPOJO(emailIds.asList())
        } else {
            EasyIdsPOJO(ArrayList())
        }
    }
}