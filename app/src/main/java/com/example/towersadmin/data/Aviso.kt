package com.example.towersadmin.data

data class Aviso(
    var aviso_id: Int,
    var titulo_aviso: String,
    var data_hora: String,
    var aviso: String,
    var status_aviso: String?,
    var link: String?,
    var condonminio_id : Int
)
