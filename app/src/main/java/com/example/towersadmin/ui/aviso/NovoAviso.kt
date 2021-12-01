package com.example.towersadmin.ui.aviso

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
import com.example.towersadmin.resquests.AvisoReq
import com.example.towersadmin.responses.AvisoRes
import com.example.towersadmin.ui.dashboards.DashBoardActivity
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
        val tv_data: EditText = findViewById(R.id.tv_data)
        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        val btnNovoAviso: Button = findViewById(R.id.btn_criar_novo_aviso)


        val hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
        val condominio_id = dados.getInt("condominio_id", 0)


        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }

        btnNovoAviso.setOnClickListener {

            if (tv_mensagem.text.isEmpty() || tv_titulo.text.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos obrigatórios!", Toast.LENGTH_LONG).show()
            } else {
                val aviso = AvisoReq(
                    tv_titulo.text.toString(), tv_mensagem.text.toString(), tv_link.text.toString(),
                    tv_status.text.toString(), tv_data.text.toString(), condominio_id
                )

                val remote = ApiClient().retrofitService()

                remote.novoAviso(aviso)
                    .enqueue(object : Callback<AvisoRes> {
                        override fun onResponse(
                            call: Call<AvisoRes>,
                            response: Response<AvisoRes>
                        ) {

                            Toast.makeText(
                                applicationContext,
                                "Aviso criado com sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
                            abrirAvisos()

                            val responseAviso = response.body()
                            Log.i("avisoresponse", responseAviso.toString())

                        }

                        override fun onFailure(call: Call<AvisoRes>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Algo deu errado! Tente novamente mais tarde.",
                                Toast.LENGTH_LONG
                            ).show()
                            Log.i("avisoResponse", t.toString())
                        }
                    })
            }
        }

    }
    private fun abrirAvisos() {
        val intent = Intent(this, activity_aviso::class.java)
        startActivity(intent)
    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }
}