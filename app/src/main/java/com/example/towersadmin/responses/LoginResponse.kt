package com.example.towersadmin.responses

import com.example.towersadmin.data.Apartamento
import com.example.towersadmin.data.Bloco
import com.example.towersadmin.data.Condominio
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("moradorId")
    var moradorId: Int,

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
