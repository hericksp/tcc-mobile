package com.example.towersadmin.ui.visitantes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.towersadmin.R
import com.example.towersadmin.adapters.VisitasAdapter
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.data.Visitas
import com.example.towersadmin.ui.dashboards.DashBoardActivity
import com.example.towersadmin.ui.dashboards.DashBoardMorador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VisitantesMor : AppCompatActivity() {

    lateinit var rvVisitantes : RecyclerView
    lateinit var visitasAdapter: VisitasAdapter

    lateinit var iv_voltar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitantes_mor)

        rvVisitantes = findViewById(R.id.rv_visitas)
        visitasAdapter = VisitasAdapter(this)

        iv_voltar = findViewById(R.id.iv_voltar)

        rvVisitantes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rvVisitantes.adapter = visitasAdapter

        listarVisitantes()

        iv_voltar.setOnClickListener {
            finish()
        }
    }

    private fun abrirDashBoardMorador() {
        val intent = Intent(this, DashBoardMorador::class.java)
        startActivity(intent)
    }

    private fun listarVisitantes(){

        val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
        val sindico_id = dados.getInt("user_id", 0)
        val remote = ApiClient().retrofitService()

        val call: Call<List<Visitas>> = remote.ListarVisitasMor()
        call.enqueue(object : Callback<List<Visitas>>{
            override fun onResponse(call: Call<List<Visitas>>, response: Response<List<Visitas>>) {
                val visitas = response.body()

                visitasAdapter.updateListaVisita(visitas!!)

                Log.i("Visitas", visitas.toString())            }

            override fun onFailure(call: Call<List<Visitas>>, t: Throwable) {
                Toast.makeText(this@VisitantesMor, "Algo deu errado", Toast.LENGTH_LONG).show()
                Log.i("XPTO", t.message.toString())            }

        })
    }
}