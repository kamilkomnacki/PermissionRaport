package com.komnacki.permissionraport

import com.google.gson.annotations.SerializedName

class Contact {
    @SerializedName("name")
    val name : String? = null

    @SerializedName("phone_number")
    val phone_number : String? = null

    @SerializedName("id")
    val id : Long? = null

    @SerializedName("email")
    val email : String? = null

    override fun toString() : String {
        return "[name = $name, phone_number = $phone_number, id = $id, email = $email]"
    }
}