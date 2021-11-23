package com.example.towersadmin.data

import com.google.gson.annotations.SerializedName

data class CadastroVisitanteReq(

        @SerializedName("morador_id")
        var morador_id: Int,

        @SerializedName("name")
        var name: String,

        @SerializedName("rg")
        var rg: String,

        @SerializedName("cpf")
        var cpf: String,

        @SerializedName("image")
        var image: String


)
