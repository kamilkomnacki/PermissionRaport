package com.komnacki.permissionraport.contacts

import com.google.gson.annotations.SerializedName

class ContactPOJO {
    constructor()
    constructor(
        id : Long,
        displayName : String,
        firstName : String,
        lastName : String,
        nickname : String,
        nicknameType : String,
        number : String,
        normalizedNumber : String,
        numberType : String,
        email : String,
        emailLabel : String,
        emailType : String,
        website : String,
        eventStartDate : String,
        eventLabel : String,
        eventType : String,
        note : String,
        address : String,
        addressType : String
    ) {
        this.id = id
        this.displayName = displayName
        this.firstName = firstName
        this.lastName = lastName
        this.nickname = nickname
        this.nicknameType = nicknameType
        this.number = number
        this.normalizedNumber = normalizedNumber
        this.numberType = numberType
        this.email = email
        this.emailLabel = emailLabel
        this.emailType = emailType
        this.website = website
        this.eventStartDate = eventStartDate
        this.eventLabel = eventLabel
        this.eventType = eventType
        this.note = note
        this.address = address
        this.addressType = addressType
    }

    @SerializedName("id")
    var id : Long? = null

    @SerializedName("displayName")
    var displayName : String? = null

    @SerializedName("firstName")
    var firstName : String? = null

    @SerializedName("lastName")
    var lastName : String? = null

    @SerializedName("nickname")
    var nickname : String? = null

    @SerializedName("nicknameType")
    var nicknameType : String? = null

    @SerializedName("number")
    var number : String? = null

    @SerializedName("normalizedNumber")
    var normalizedNumber : String? = null

    @SerializedName("numberType")
    var numberType : String? = null

    @SerializedName("email")
    var email : String? = null

    @SerializedName("emailLabel")
    var emailLabel : String? = null

    @SerializedName("emailType")
    var emailType : String? = null

    @SerializedName("website")
    var website : String? = null

    @SerializedName("eventStartDate")
    var eventStartDate : String? = null

    @SerializedName("eventLabel")
    var eventLabel : String? = null

    @SerializedName("eventType")
    var eventType : String? = null

    @SerializedName("note")
    var note : String? = null

    @SerializedName("address")
    var address : String? = null

    @SerializedName("addressType")
    var addressType : String? = null


    override fun toString() : String {
        return "[id = $id," +
                " displayName = $displayName," +
                " firstName = $firstName," +
                " lastName = $lastName," +
                " nickName = $nickname," +
                " nicknameType = $nicknameType," +
                " number = $number," +
                " normalizedNumber = $normalizedNumber," +
                " numberType = $numberType," +
                " email = $email," +
                " emailLabel = $emailLabel," +
                " emailType = $emailType," +
                " website = $website," +
                " eventStartDate = $eventStartDate," +
                " eventLaber = $eventLabel," +
                " eventType = $eventType," +
                " note = $note," +
                " address = $address," +
                " addressType = $addressType"
    }
}