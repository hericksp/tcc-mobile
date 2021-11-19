package com.example.towersadmin.api

import com.example.towersadmin.data.*
import com.example.towersadmin.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST(Constants.SINDICO_URL)
    fun loginSindico(@Body loginRequest: LoginRequest): Call<LoginSindicoResponse>

    @POST(Constants.VISITANTE_MORADOR_URL)
    fun visitanteMorador(@Body cadastroVisitanteReq: CadastroVisitanteReq): Call<VisitanteMoradorRes>

    @POST(Constants.VISITANTE_SINDICO_URL)
    fun visitanteSindico(@Body cadastroVisitanteReq: CadastroVisitanteReq): Call<VisitanteSindicoRes>
}