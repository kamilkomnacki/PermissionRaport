package com.komnacki.permissionraport

class PermissionItem(
    val name : String,
    val manifest : String,
    var isChecked : Boolean,
    val data : String,
    val objectClass : PojoFeeder
) {
    override fun toString() : String {
        return name
    }
}
