package com.example.towersadmin.api

import com.example.towersadmin.data.*
import com.example.towersadmin.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST(Constants.SINDICO_URL)
    fun loginSindico(@Body loginRequest: LoginRequest): Call<LoginSindicoResponse>

    @POST(Constants.VISITANTE_MORADOR_URL)
    fun visitanteMorador(@Body cadastroVisitanteReq: CadastroVisitanteReq): Call<VisitanteMoradorRes>

    @POST(Constants.VISITANTE_SINDICO_URL)
    fun visitanteSindico(@Body cadastroVisitanteReq: CadastroVisitanteReq): Call<VisitanteSindicoRes>

    @GET(Constants.AVISOS_URL)
    fun listarAvisos(@Path("id")id: Int) : Call<List<Aviso>>

    @POST(Constants.AVISOS_URL)
    fun novoAviso(@Body aviso: Aviso) : Call<Aviso>


}