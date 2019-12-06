package com.komnacki.permissionraport.contacts

import android.app.Activity
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import io.reactivex.Observable
import jagerfield.mobilecontactslibrary.ImportContacts

class Contacts : PojoFeeder {
    constructor(activity : Activity) {
        this.importContacts = ImportContacts(activity)
    }

    var importContacts : ImportContacts? = null

    override fun getPOJO() : POJO {
        return getContacts()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendContacts(email, getContacts())
    }

    private fun getContacts() : ContactsPOJO {
        return ContactsPOJO(importContacts !!.contacts)
    }
}