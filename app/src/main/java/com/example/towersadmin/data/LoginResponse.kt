package com.example.towersadmin.data

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

    @SerializedName("token")
    var token: String,
)
