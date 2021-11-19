package com.example.towersadmin.data

import com.google.gson.annotations.SerializedName

data class CadastroVisitanteSindicoReq(

        @SerializedName("name")
        var name: String,

        @SerializedName("rg")
        var rg: String,

        @SerializedName("cpf")
        var cpf: String,

        @SerializedName("image")
        var image: String,

        @SerializedName("sindico_id")
        var sindico_id: Int
)
