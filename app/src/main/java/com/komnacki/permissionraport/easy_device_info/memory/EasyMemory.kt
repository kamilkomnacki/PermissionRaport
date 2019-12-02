package com.komnacki.permissionraport.easy_device_info.memory

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyMemoryMod
import io.reactivex.Observable

class EasyMemory : PojoFeeder {
    constructor(context : Context) {
        this.easyMemoryMod = EasyMemoryMod(context)
    }

    var easyMemoryMod : EasyMemoryMod? = null

    override fun getPOJO() : EasyMemoryPOJO {
        return getMemory()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendMemoryInfo(email, getPOJO())
    }

    fun getMemory() : EasyMemoryPOJO {
        return EasyMemoryPOJO(
            easyMemoryMod !!.convertToMb(easyMemoryMod !!.totalRAM),
            easyMemoryMod !!.convertToMb(easyMemoryMod !!.availableInternalMemorySize),
            easyMemoryMod !!.convertToMb(easyMemoryMod !!.availableExternalMemorySize),
            easyMemoryMod !!.convertToMb(easyMemoryMod !!.totalInternalMemorySize),
            easyMemoryMod !!.convertToMb(easyMemoryMod !!.totalExternalMemorySize)
        )
    }
}