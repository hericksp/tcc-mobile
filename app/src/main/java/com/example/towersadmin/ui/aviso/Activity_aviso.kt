package com.example.towersadmin.ui.aviso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.adapters.AvisosAdapter
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.data.Avisos
import com.example.towersadmin.ui.dashboards.DashBoardActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_aviso : AppCompatActivity() {

    lateinit var rvAvisos : RecyclerView
    lateinit var avisosAdapter : AvisosAdapter

    lateinit var btn_novo_aviso : Button
    lateinit var iv_voltar : Button
    lateinit var tv_deletar : TextView

    lateinit var titulo_aviso : RecyclerView
    lateinit var data_hora : TextView
    lateinit var editar_aviso : TextView
    lateinit var main_aviso : TextView
    lateinit var status : TextView
    lateinit var link : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aviso)

        //Configuração da RecyclerView
        rvAvisos = findViewById(R.id.rv_avisos)
        avisosAdapter = AvisosAdapter(this)

        btn_novo_aviso = findViewById(R.id.btn_novo_aviso)
        iv_voltar = findViewById(R.id.iv_voltar)

        /*data_hora = findViewById(R.id.data_hora_aviso)
        editar_aviso = findViewById(R.id.tv_editar_aviso)
        main_aviso = findViewById(R.id.tv_main_aviso)
        status = findViewById(R.id.tv_status_aviso)
        link = findViewById(R.id.tv_link)*/

        rvAvisos.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)

        rvAvisos.adapter = avisosAdapter

        val remote = ApiClient().retrofitService()

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
        val condominio_id = dados.getInt("condominio_id", 0)

        val call: Call<List<Avisos>> = remote.listarAvisos(condominio_id)

            call.enqueue(object : Callback<List<Avisos>>{
                override fun onResponse(call: Call<List<Avisos>>, response: Response<List<Avisos>>) {

                    val avisos = response.body()

                    avisosAdapter.updateListaAviso(avisos!!)

                    Log.i("XPTO", avisos.toString())
                }

                override fun onFailure(call: Call<List<Avisos>>, t: Throwable) {
                    Toast.makeText(this@activity_aviso, "Algo deu errado!", Toast.LENGTH_LONG).show()
                    Log.i("XPTO", t.message.toString())

                }

            })


        iv_voltar.setOnClickListener {
            finish()
        }

        btn_novo_aviso.setOnClickListener {
            novoAviso()
        }
    }

    private fun novoAviso(){
        val intent = Intent(this, NovoAviso()::class.java)
        startActivity(intent)
        finish()
    }
}