package com.example.towersadmin.resquests

import android.graphics.Bitmap
import android.util.Base64
import com.google.gson.annotations.SerializedName
import java.util.*

data class CadastroVisitanteSindicoReq(

        @SerializedName("sindico_id")
        var sindico_id: Int,

        @SerializedName("name")
        var name: String,

        @SerializedName("rg")
        var rg: String,

        @SerializedName("cpf")
        var cpf: String,

        @SerializedName("image")
        var image: ByteArray?

)
