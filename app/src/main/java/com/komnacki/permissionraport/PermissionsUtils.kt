package com.komnacki.permissionraport

class PermissionsUtils {
    companion object {
        private var defaultStatus = false

        val PERMISSIONS_LIST = listOf(
            PermissionItem("Kontakty", defaultStatus),
            PermissionItem("Wiadomości", defaultStatus),
            PermissionItem("Pamięć urządzenia", defaultStatus),
            PermissionItem("Aparat", defaultStatus),
            PermissionItem("Dyktafon", defaultStatus),
            PermissionItem("Galeria", defaultStatus)
        )
    }
}