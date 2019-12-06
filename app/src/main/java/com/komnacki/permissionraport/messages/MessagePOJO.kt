package com.komnacki.permissionraport.messages

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class MessagePOJO : POJO {
    constructor()
    constructor(
        id : Long,
        threadId : Long,
        addressNumber : String,
        person : String,
        date : Long,
        dateSend : Long,
        protocol : Long,
        read : String,
        status : String,
        type : String,
        subject : String,
        body : String
    ) {
        this.id = id
        this.threadId = threadId
        this.addressNumber = addressNumber
        this.person = person
        this.date = date
        this.dateSend = dateSend
        this.protocol = protocol
        this.read = read
        this.status = status
        this.type = type
        this.subject = subject
        this.body = body

    }

    @SerializedName("id")
    var id : Long? = null

    @SerializedName("threadId")
    var threadId : Long? = null

    @SerializedName("addressNumber")
    var addressNumber : String? = null

    @SerializedName("person")
    var person : String? = null

    @SerializedName("date")
    var date : Long? = null

    @SerializedName("dateSend")
    var dateSend : Long? = null

    @SerializedName("read")
    var read : String? = null

    @SerializedName("protocol")
    var protocol : Long? = null

    @SerializedName("status")
    var status : String? = null

    @SerializedName("type")
    var type : String? = null

    @SerializedName("subject")
    var subject : String? = null

    @SerializedName("body")
    var body : String? = null


    override fun toString() : String {
        return "[id = $id," +
                " threadId = $threadId," +
                " addressNumber = $addressNumber," +
                " person = $person," +
                " date = $date, " +
                " dateSend = $dateSend," +
                " protocol = $protocol" +
                " read = $read" +
                " dateSend = $dateSend" +
                " status = $status" +
                " type = $type" +
                " subject = $subject" +
                " body = $body"
    }
}