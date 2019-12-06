package com.komnacki.permissionraport.messages

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import io.reactivex.Observable

class Messages : PojoFeeder {
    constructor(activity : Activity) {
        this.contentResolver = activity.contentResolver
    }

    var contentResolver : ContentResolver? = null

    override fun getPOJO() : POJO {
        val inbox = getMessagesInbox()
        val outbox = getMessagesOutbox()
        val allMessages = inbox + outbox
        return MessagesPOJO(allMessages)
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {

        return service.sendMessages(email, getPOJO() as MessagesPOJO)
    }

    private fun getMessagesInbox() : List<MessagePOJO> {
        var list = ArrayList<MessagePOJO>()
        val cursorInbox = contentResolver !!.query(Uri.parse("content://sms/inbox"), null, null, null, null)
        var counter = 0
        if (cursorInbox !!.moveToFirst()) { // must check the result to prevent exception
            while (cursorInbox.moveToNext()) {
                if (counter < 20) {
                    counter ++
                    Log.d("MESSAGE inbox", cursorInbox.toString())

                    //Przesuniecie, bo wybieram nie wszystkie pola
                    list.add(
                        MessagePOJO(
                            if (cursorInbox.isNull(0)) - 1 else cursorInbox.getLong(0),
                            if (cursorInbox.isNull(1)) - 1 else cursorInbox.getLong(1),
                            if (cursorInbox.isNull(2)) "Brak danych" else cursorInbox.getString(2),
                            if (cursorInbox.isNull(3)) "Brak danych" else cursorInbox.getString(3),
                            if (cursorInbox.isNull(4)) - 1 else cursorInbox.getLong(4),
                            if (cursorInbox.isNull(5)) - 1 else cursorInbox.getLong(5),
                            if (cursorInbox.isNull(6)) - 1 else cursorInbox.getLong(6),
                            if (cursorInbox.isNull(7)) "Brak danych" else cursorInbox.getString(7),
                            if (cursorInbox.isNull(8)) "Brak danych" else cursorInbox.getString(8),
                            if (cursorInbox.isNull(9)) "Brak danych" else cursorInbox.getString(9),
                            if (cursorInbox.isNull(11)) "Brak danych" else cursorInbox.getString(11),
                            if (cursorInbox.isNull(12)) "Brak danych" else cursorInbox.getString(12)
                        )
                    )

                    var msgData = ""

                    for (idx in 0 until cursorInbox.columnCount) {
                        msgData += " " + cursorInbox.getColumnName(idx) + ":" + cursorInbox.getString(idx)
                    }
                    Log.d("MESSAGE2 inbox", "msg: $msgData")


                } else {
                    break
                }
            }
        } else {
        }
        cursorInbox.close()
        return list
    }

    private fun getMessagesOutbox() : List<MessagePOJO> {
        var list = ArrayList<MessagePOJO>()
        val cursorOutbox = contentResolver !!.query(Uri.parse("content://sms/outbox"), null, null, null, null)
        var counter = 0
        if (cursorOutbox !!.moveToFirst()) { // must check the result to prevent exception
            while (cursorOutbox.moveToNext()) {
                if (counter < 20) {
                    counter ++
                    Log.d("MESSAGE outbox", cursorOutbox.toString())

                    //Przesuniecie, bo wybieram nie wszystkie pola
                    list.add(
                        MessagePOJO(
                            if (cursorOutbox.isNull(0)) - 1 else cursorOutbox.getLong(0),
                            if (cursorOutbox.isNull(1)) - 1 else cursorOutbox.getLong(1),
                            if (cursorOutbox.isNull(2)) "Brak danych" else cursorOutbox.getString(2),
                            if (cursorOutbox.isNull(3)) "Brak danych" else cursorOutbox.getString(3),
                            if (cursorOutbox.isNull(4)) - 1 else cursorOutbox.getLong(4),
                            if (cursorOutbox.isNull(5)) - 1 else cursorOutbox.getLong(5),
                            if (cursorOutbox.isNull(6)) - 1 else cursorOutbox.getLong(6),
                            if (cursorOutbox.isNull(7)) "Brak danych" else cursorOutbox.getString(7),
                            if (cursorOutbox.isNull(8)) "Brak danych" else cursorOutbox.getString(8),
                            if (cursorOutbox.isNull(9)) "Brak danych" else cursorOutbox.getString(9),
                            if (cursorOutbox.isNull(11)) "Brak danych" else cursorOutbox.getString(11),
                            if (cursorOutbox.isNull(12)) "Brak danych" else cursorOutbox.getString(12)
                        )
                    )

                    var msgData = ""

                    for (idx in 0 until cursorOutbox.columnCount) {
                        msgData += " " + cursorOutbox.getColumnName(idx) + ":" + cursorOutbox.getString(idx)
                    }
                    Log.d("MESSAGE2 outbox", "msg: $msgData")


                } else {
                    break
                }
            }
        } else {
        }
        cursorOutbox.close()
        return list
    }
}