package com.komnacki.permissionraport

import com.google.gson.annotations.SerializedName
import com.komnacki.read_contacts_permissions.Contact


class ContactsPOJO {
    constructor()
    constructor(listOfContacts : List<Contact>) {
        for (i in listOfContacts.indices) {
            this.data?.set(i, ContactPOJO(listOfContacts[i].id, listOfContacts[i].name, listOfContacts[i].email, listOfContacts[i].phoneNumber))
        }
    }
    @SerializedName("contacts")
    var data : Array<ContactPOJO>? = null

    override fun toString() : String {
        val string = StringBuilder()
        data !!.forEach { string.append(it.toString()) }
        return "Kontakty [contacts = $string]"
    }

}