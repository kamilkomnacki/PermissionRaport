package com.komnacki.permissionraport.contacts

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO


class ContactsPOJO : POJO {
    @SerializedName("contacts")
    var data : Array<ContactPOJO>? = null

    constructor()
    constructor(listOfContacts : List<jagerfield.mobilecontactslibrary.Contact.Contact>) {
        data = Array(listOfContacts.size) { i ->
            ContactPOJO(
                listOfContacts[i].id,
                listOfContacts[i].displaydName,
                listOfContacts[i].firstName,
                listOfContacts[i].lastName,
                if (! listOfContacts[i].nickNames.isNullOrEmpty()) listOfContacts[i].nickNames[0].nickName else "",
                if (! listOfContacts[i].nickNames.isNullOrEmpty()) listOfContacts[i].nickNames[0].nickNameType else "",
                if (! listOfContacts[i].numbers.isNullOrEmpty()) listOfContacts[i].numbers[0].numlabel else "",
                if (! listOfContacts[i].numbers.isNullOrEmpty()) listOfContacts[i].numbers[0].normalizedNumber else "",
                if (! listOfContacts[i].numbers.isNullOrEmpty()) listOfContacts[i].numbers[0].normalizedNumber else "",
                if (! listOfContacts[i].emails.isNullOrEmpty()) listOfContacts[i].emails[0].email else "",
                if (! listOfContacts[i].emails.isNullOrEmpty()) listOfContacts[i].emails[0].emailLabel else "",
                if (! listOfContacts[i].emails.isNullOrEmpty()) listOfContacts[i].emails[0].emailType else "",
                if (! listOfContacts[i].websites.isNullOrEmpty()) listOfContacts[i].websites[0].website else "",
                if (! listOfContacts[i].events.isNullOrEmpty()) listOfContacts[i].events[0].eventStartDate else "",
                if (! listOfContacts[i].events.isNullOrEmpty()) listOfContacts[i].events[0].eventLabel else "",
                if (! listOfContacts[i].events.isNullOrEmpty()) listOfContacts[i].events[0].eventType else "",
                if (! listOfContacts[i].notes.isNullOrEmpty()) listOfContacts[i].notes[0].note else "",
                if (! listOfContacts[i].addresses.isNullOrEmpty()) listOfContacts[i].addresses[0].address else "",
                if (! listOfContacts[i].addresses.isNullOrEmpty()) listOfContacts[i].addresses[0].addressType else ""
            )
        }
    }

    override fun toString() : String {
        val string = StringBuilder()
        data !!.forEach { string.append(it.toString()) }
        return "Kontakty: [contacts = $string]"
    }

}