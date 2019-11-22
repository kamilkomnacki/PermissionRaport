package com.komnacki.permissionraport

class PermissionItem(
    val name : String,
    val manifest : String,
    var isChecked : Boolean
) {
    override fun toString() : String {
        return name
    }
}
