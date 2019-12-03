package com.komnacki.permissionraport.easy_device_info.nfc

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyNfcMod
import io.reactivex.Observable

class EasyNFC : PojoFeeder {
    constructor(context : Context) {
        this.easyNFCMod = EasyNfcMod(context)
    }

    var easyNFCMod : EasyNfcMod? = null

    override fun getPOJO() : POJO {
        return getEasyNFCPojo() //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendNFCInfo(email, getEasyNFCPojo())
    }

    fun getEasyNFCPojo() : EasyNFCPOJO {
        return EasyNFCPOJO(
            easyNFCMod !!.isNfcPresent,
            easyNFCMod !!.isNfcEnabled
        )
    }
}