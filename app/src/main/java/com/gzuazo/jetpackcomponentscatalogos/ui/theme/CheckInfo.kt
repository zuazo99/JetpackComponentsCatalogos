package com.gzuazo.jetpackcomponentscatalogos.ui.theme

data class CheckInfo(
    val title: String,
    var selected: Boolean = false,
    var onCheckedChange: (Boolean) -> Unit
)
