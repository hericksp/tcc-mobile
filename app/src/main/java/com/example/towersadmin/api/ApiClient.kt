package com.example.towersadmin.api

import com.example.towersadmin.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Retrofit instance class
 */
class ApiClient {
    
    val retrofitFactory = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun  retrofitService() : ApiService {
        return  retrofitFactory.create(ApiService::class.java)
    }

}