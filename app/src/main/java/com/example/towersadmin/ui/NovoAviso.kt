package com.example.towersadmin.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.towersadmin.R
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.data.Aviso
import com.example.towersadmin.data.AvisoRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NovoAviso : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novo_aviso)

        val tv_titulo: EditText = findViewById(R.id.tv_titulo)
        val tv_mensagem: EditText = findViewById(R.id.tv_mensagem)
        val tv_link: EditText = findViewById(R.id.tv_link)
        val tv_status: EditText = findViewById(R.id.tv_status)
        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        val btnNovoAviso: Button = findViewById(R.id.btn_criar_novo_aviso)


        val hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
        val condominio_id = dados.getInt("condominio_id", 0)


        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }

        btnNovoAviso.setOnClickListener {


            val avisoRes:AvisoRes = AvisoRes(30, tv_titulo.text.toString(), hoje.toString(), tv_mensagem.text.toString(), tv_status.text.toString(),
                tv_link.text.toString())
            val aviso:Aviso = Aviso(avisoRes,condominio_id)


            val remote = ApiClient().retrofitService()


            val call: Call<Aviso> = remote.novoAviso(aviso)

            call.enqueue(object : Callback<Aviso>{
                override fun onResponse(call: Call<Aviso>, response: Response<Aviso>) {
                    //val reponse = response.body()
                    Toast.makeText(applicationContext, "Aviso criado com sucesso!", Toast.LENGTH_LONG).show()
                    abrirDashBoard()
                    Log.i("avisoResponse", response.body().toString())
                    Log.i("avisoResponse", response.code().toString())

                }

                override fun onFailure(call: Call<Aviso>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    Log.i("avisoResponse", "DEU MERDA")}
            })
        }

    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }
}