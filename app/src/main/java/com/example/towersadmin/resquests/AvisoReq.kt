package com.example.towersadmin.resquests

data class AvisoReq(
    var titulo: String,
    var mensagem: String,
    var link: String?,
    var status: String?,
    var data: String?,
    var condominio_id: Int
)
