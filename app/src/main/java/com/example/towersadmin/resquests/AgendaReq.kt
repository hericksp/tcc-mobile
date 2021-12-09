package com.example.towersadmin.resquests

data class AgendaReq(
    var nome: String,
    var data: String,
    var horainicio: String,
    var horatermino: String,
    var condominio_id: Int
)
