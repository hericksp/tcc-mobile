package com.example.towersadmin.resquests

data class AgendaReq(
    var nome: String,
    var data: String,
    var horaInicio: String,
    var horaTermino: String,
    var condominio_id: Int
)
