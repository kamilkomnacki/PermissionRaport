package com.komnacki.permissionraport

import com.google.gson.annotations.SerializedName
import com.komnacki.read_contacts_permissions.Contact


class ContactsPOJO {
    @SerializedName("contacts")
    var data : Array<ContactPOJO>? = null

    constructor()
    constructor(listOfContacts : List<Contact>) {
        data = Array(listOfContacts.size) { i ->
            ContactPOJO(
                listOfContacts[i].id,
                listOfContacts[i].name,
                listOfContacts[i].email,
                listOfContacts[i].phoneNumber
            )
        }
    }

    override fun toString() : String {
        val string = StringBuilder()
        data !!.forEach { string.append(it.toString()) }
        return "Kontakty: [contacts = $string]"
    }

}