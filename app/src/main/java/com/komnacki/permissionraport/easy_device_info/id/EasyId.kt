package com.komnacki.permissionraport.easy_device_info.id

import android.content.Context
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyIdMod
import io.reactivex.Observable

class EasyId : PojoFeeder {
    constructor(context : Context) {
        this.easyIdMod = EasyIdMod(context)
    }

    var easyIdMod : EasyIdMod? = null

    override fun getPOJO() : Observable<EasyIdPOJO> {
        return Observable.just(getUserEmails())
    }

    fun getUserEmails() : EasyIdPOJO {
        //Get Google Email ID
        val emailIds = easyIdMod?.accounts
        val emailString = StringBuilder()
        if (emailIds != null && emailIds.isNotEmpty()) {
            for (e in emailIds) {
                emailString.append(e).append("\n")
            }
        } else {
            emailString.append("Nie znaleziono")
        }

        return EasyIdPOJO(emailString.toString())
    }
}