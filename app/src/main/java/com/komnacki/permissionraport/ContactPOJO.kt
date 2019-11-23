package com.komnacki.permissionraport

import com.google.gson.annotations.SerializedName

class ContactPOJO {
    constructor()
    constructor(id : Long, name : String, email : String, phone_number : String) {
        this.id = id
        this.name = name
        this.email = email
        this.phoneNumber = phone_number
    }

    @SerializedName("name")
    var name : String? = null

    @SerializedName("phoneNumber")
    var phoneNumber : String? = null

    @SerializedName("id")
    var id : Long? = null

    @SerializedName("email")
    var email : String? = null


    override fun toString() : String {
        return "[name = $name, phoneNumber = $phoneNumber, id = $id, email = $email]"
    }
}