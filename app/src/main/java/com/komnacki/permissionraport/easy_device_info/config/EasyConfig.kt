package com.komnacki.permissionraport.easy_device_info.config

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyConfigMod
import github.nisrulz.easydeviceinfo.base.RingerMode
import io.reactivex.Observable

class EasyConfig : PojoFeeder {
    constructor(context : Context) {
        this.easyConfigMod = EasyConfigMod(context)
    }

    var easyConfigMod : EasyConfigMod? = null

    override fun getPOJO() : EasyConfigPOJO {
        return getConfig()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendConfig(email, getConfig())
    }

    fun getConfig() : EasyConfigPOJO {
        return when (easyConfigMod?.deviceRingerMode) {
            RingerMode.NORMAL -> EasyConfigPOJO("Normalny")
            RingerMode.VIBRATE -> EasyConfigPOJO("Wibracje")
            RingerMode.SILENT -> EasyConfigPOJO("Wyciszony")
            else -> EasyConfigPOJO("Niezidentyfikowany tryb")
        }
    }
}