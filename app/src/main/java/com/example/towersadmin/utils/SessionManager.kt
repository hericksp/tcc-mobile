package com.example.towersadmin.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.towersadmin.R

class SessionManager(context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_ID = "user_id"
        const val USER_NAME = "user_name"
        const val USER_SURNAME = "user_surname"
        const val USER_CPF = "user_cpf"
        const val USER_BIRTH = "user_birth"
        const val USER_EMAIL = "user_email"
        const val USER_TOKEN = "user_token"
        const val LEMBRAR = "lembrar"
    }

    //Função para salvar o token de autenticação
    fun saveAuthToken( id:Int, name:String, surname:String, cpf:String, birth:String, email:String, token:String, lembrar:Boolean){

        val editor = prefs.edit()
        editor.putInt(USER_ID, id)
        editor.putString(USER_NAME, name)
        editor.putString(USER_SURNAME, surname)
        editor.putString(USER_CPF, cpf)
        editor.putString(USER_BIRTH, birth)
        editor.putString(USER_EMAIL, email)
        editor.putString(USER_TOKEN, token)
        editor.putBoolean(LEMBRAR, lembrar)
        editor.apply()
    }

    //Função Fetch do token de autenticação

    fun fetchAuthToken(): String?{
        return prefs.getString(USER_TOKEN, null)
    }


}