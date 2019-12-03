package com.komnacki.permissionraport.easy_device_info.sim

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasySimMod
import io.reactivex.Observable

class EasySim : PojoFeeder {
    constructor(context : Context) {
        this.easySimMod = EasySimMod(context)
    }

    var easySimMod : EasySimMod? = null

    override fun getPOJO() : POJO {
        return getSimInfo()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendSimInfo(email, getSimInfo())
    }

    private fun getSimInfo() : EasySimPOJO {
        var simSerial : String
        var isMultiSim : Boolean
        var numberOfActiveSim : Int
        try {
            simSerial = easySimMod !!.simSerial
            isMultiSim = easySimMod !!.isMultiSim
            numberOfActiveSim = easySimMod !!.numberOfActiveSim
        } catch (e : SecurityException) {
            simSerial = "Nieznany"
            isMultiSim = false
            numberOfActiveSim = - 1
        }

        return EasySimPOJO(
            simSerial,
            easySimMod !!.country,
            easySimMod !!.carrier,
            if (easySimMod !!.isSimNetworkLocked) 1 else 0,
            if (isMultiSim) 1 else 0,
            numberOfActiveSim
        )
    }
}