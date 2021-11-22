package com.example.towersadmin.data

import com.google.gson.annotations.SerializedName

data class LoginSindicoResponse(

    @SerializedName("sindicoId")
    var sindicoId: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("surname")
    var surname: String,

    @SerializedName("cpf")
    var cpf: String,

    @SerializedName("birth")
    var birth: String,

    @SerializedName("email")
    var email: String,

    var apartamento: Apartamento,

    var bloco: Bloco,

    var condominio: Condominio,

    @SerializedName("token")
    var token: String,
)
