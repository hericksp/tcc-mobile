package com.example.towersadmin.ui.agendamento

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
import com.example.towersadmin.adapters.AgendamentosAdapter
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.data.Agendamentos
import com.example.towersadmin.resquests.AgendaReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Agendamentos : AppCompatActivity() {

    lateinit var rvAgendamentos : RecyclerView
    lateinit var agendamentosAdapter: AgendamentosAdapter

    lateinit var iv_voltar : Button
    lateinit var tv_novo_agend : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendamentos)

        rvAgendamentos = findViewById(R.id.rv_agendamentos)
        agendamentosAdapter = AgendamentosAdapter(this)

        iv_voltar = findViewById(R.id.iv_voltar)
        tv_novo_agend = findViewById(R.id.btn_novo_agend)

        rvAgendamentos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvAgendamentos.adapter = agendamentosAdapter

        val remote = ApiClient().retrofitService()

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
        val condominioId = dados.getInt("condominio_id", 0)

        val call : Call<List<Agendamentos>> = remote.listarAgendamentos(condominioId)

        call.enqueue(object : Callback<List<Agendamentos>>{
            override fun onResponse(call: Call<List<Agendamentos>>, response: Response<List<Agendamentos>>) {

                val agendamentos = response.body()

                agendamentosAdapter.updateListaAgendamentos(agendamentos!!)

                Log.i("XPTO", agendamentos.toString())
            }

            override fun onFailure(call: Call<List<Agendamentos>>, t: Throwable) {
                Toast.makeText(this@Agendamentos, "Algo deu errado!", Toast.LENGTH_LONG).show()
                Log.i("XPTO", t.message.toString())
            }

        })

        iv_voltar.setOnClickListener {
            finish()
        }

        tv_novo_agend.setOnClickListener {
            novoAgendamento()
        }

    }

    private fun novoAgendamento(){
        val intent = Intent(this, AgendamentoEspaco()::class.java)
        startActivity(intent)
    }
}