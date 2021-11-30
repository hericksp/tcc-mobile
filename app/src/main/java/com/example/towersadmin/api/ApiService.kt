package com.example.towersadmin.api

import com.example.towersadmin.data.*
import com.example.towersadmin.responses.*
import com.example.towersadmin.resquests.AvisoReq
import com.example.towersadmin.resquests.LoginRequest
import com.example.towersadmin.utils.Constants
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.Part
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST(Constants.SINDICO_URL)
    fun loginSindico(@Body loginRequest: LoginRequest): Call<LoginSindicoResponse>

    @Multipart
    @POST(Constants.VISITANTE_MORADOR_URL)
    fun cadastroVisitante(
        @Path("morador_id") id: Int,
        @Part(value = "name") name: String,
        @Part(value = "rg") rg: String,
        @Part(value = "data") data: String,
        @Part image: MultipartBody.Part,
        @Part(value = "morador_id") morador_id: Int) : Call<VisitanteMoradorRes>

    @Multipart
    @POST(Constants.VISITANTE_SINDICO_URL)
    fun cadastroVisitanteSindico(
        @Path("sindico_id") id: Int,
        @Part(value = "name") name: String,
        @Part(value = "rg") rg: String,
        @Part(value = "data") data: String,
        @Part image: MultipartBody.Part,
        @Part(value = "sindico_id") sindico_id: Int) : Call<VisitanteSindicoRes>

    @GET(Constants.AVISOS_URL)
    fun listarAvisos() : Call<List<Avisos>>

    @POST(Constants.AVISOS_URL)
    fun novoAviso(@Body avisoReq: AvisoReq) : Call<AvisoRes>

    @DELETE(Constants.DELETAR_AVISO)
    fun deletarAviso(@Path ("id")id: Int) : Call<Unit>


}