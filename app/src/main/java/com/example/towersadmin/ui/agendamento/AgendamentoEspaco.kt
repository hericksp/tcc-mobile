package com.example.towersadmin.ui.agendamento

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.towersadmin.R
import com.example.towersadmin.api.ApiClient
import com.example.towersadmin.responses.AgendaRes
import com.example.towersadmin.resquests.AgendaReq
import com.example.towersadmin.ui.dashboards.DashBoardActivity
import com.example.towersadmin.utils.Mask
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class AgendamentoEspaco : AppCompatActivity(), CalendarView.OnDateChangeListener {

    lateinit var dataFinal : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendamento_espaco)

        val spinnerEspaco : Spinner = findViewById(R.id.spinner_espacos)
        val horaInicio : EditText = findViewById(R.id.et_hora_inicio)
        val horaTermino : EditText = findViewById(R.id.et_hora_termino)
        val selecionarData : CalendarView = findViewById(R.id.calendarView)
        val nomeResponsavel : EditText = findViewById(R.id.et_responsavel)
        val iv_voltar: Button = findViewById(R.id.iv_voltar)
        dataFinal = findViewById(R.id.dataFinal)
        selecionarData.setOnDateChangeListener(this)
        val btnAgendar : Button = findViewById(R.id.btn_agendar)

        horaInicio.addTextChangedListener(Mask.mask("##:##", horaInicio)).toString()
        horaTermino.addTextChangedListener(Mask.mask("##:##", horaTermino)).toString()

        iv_voltar.setOnClickListener {
            abrirDashBoard()
        }

        btnAgendar.setOnClickListener {
            val dados = getSharedPreferences("TowersAdmin", MODE_PRIVATE)
            if (spinnerEspaco.selectedItemPosition == 0 || dataFinal.text == "Data Selecionada" || horaTermino.text.isEmpty() || horaTermino.text.isEmpty() || nomeResponsavel.text.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos corretamente!", Toast.LENGTH_LONG).show()
            } else{
                val agendamento = AgendaReq(nomeResponsavel.text.toString(),selecionarData.toString(), horaInicio.text.toString(), horaTermino.text.toString(),dados.getInt("condominio_id", 0))

                val remote = ApiClient().retrofitService()

                remote.novoAgendamento(agendamento).enqueue(object : Callback<AgendaRes>{
                    override fun onResponse(call: Call<AgendaRes>, response: Response<AgendaRes>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AgendamentoEspaco, "Agendamento Criado com sucesso!", Toast.LENGTH_LONG).show()

                        }else{
                            Toast.makeText(this@AgendamentoEspaco, "Algo deu errado!", Toast.LENGTH_LONG).show()
                        }

                    }

                    override fun onFailure(call: Call<AgendaRes>, t: Throwable) {
                        Toast.makeText(this@AgendamentoEspaco, "Algo deu muito errado!", Toast.LENGTH_LONG).show()
                        Log.i("avisoResponse", t.toString())

                    }

                })
            }
        }



    }
    private fun abrirDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {

        var mesSelecionado = (month + 1)
        var dataF = String.format("%02d", dayOfMonth) +"/"+ String.format("%02d", mesSelecionado) +"/"+ year

        dataFinal.text = dataF

    Toast.makeText(this, "Data Selecionada: "+dataF, Toast.LENGTH_SHORT).show()

    }

}