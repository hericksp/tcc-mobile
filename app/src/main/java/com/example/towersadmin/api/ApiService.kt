package com.example.towersadmin.api

import android.graphics.Bitmap
import com.example.towersadmin.data.*
import com.example.towersadmin.utils.Constants
import okhttp3.RequestBody
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
        @Part(value = "cpf") cpf: String,
        @Part(value = "image", encoding = "8-bit") image: String,
        @Part(value = "morador_id") morador_id: Int) : Call<VisitanteMoradorRes>

    @Multipart
    @POST(Constants.VISITANTE_SINDICO_URL)
    fun cadastroVisitanteSindico(
        @Path("sindico_id") id: Int,
        @Part(value = "name") name: String,
        @Part(value = "rg") rg: String,
        @Part(value = "cpf") cpf: String,
        @Part(value = "image", encoding = "8-bit") image: String,
        @Part(value = "sindico_id") sindico_id: Int) : Call<VisitanteSindicoRes>

    @GET(Constants.AVISOS_URL)
    fun listarAvisos(@Path("id")id: Int) : Call<List<Aviso>>

    @POST(Constants.AVISOS_URL)
    fun novoAviso(@Body aviso: Aviso) : Call<Aviso>


}