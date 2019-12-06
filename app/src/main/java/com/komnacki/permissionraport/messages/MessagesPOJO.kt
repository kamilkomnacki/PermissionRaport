package com.komnacki.permissionraport.messages

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class MessagesPOJO : POJO {
    @SerializedName("messages")
    var data : Array<MessagePOJO>? = null

    constructor()
    constructor(listOfContacts : List<MessagePOJO>) {
        data = Array(listOfContacts.size) { i ->
            MessagePOJO(
                listOfContacts[i].id !!,
                listOfContacts[i].threadId !!,
                listOfContacts[i].addressNumber !!,
                listOfContacts[i].person !!,
                listOfContacts[i].date !!.toLong(),
                listOfContacts[i].dateSend !!.toLong(),
                listOfContacts[i].protocol !!,
                listOfContacts[i].read !!,
                listOfContacts[i].status !!,
                listOfContacts[i].type !!,
                listOfContacts[i].subject !!,
                listOfContacts[i].body !!
            )
        }
    }

    override fun toString() : String {
        val string = StringBuilder()
        data !!.forEach { string.append(it.toString()) }
        return "Wiadomości: [messages = $string]"
    }
}